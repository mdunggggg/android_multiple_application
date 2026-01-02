package com.mdunggggg.jamendo_music.player.controller

import android.content.ComponentName
import android.content.Context
import android.os.Handler
import android.os.Looper
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import com.google.common.util.concurrent.MoreExecutors
import com.mdunggggg.jamendo_music.player.MusicState
import com.mdunggggg.jamendo_music.player.PlaybackState
import com.mdunggggg.jamendo_music.player.RepeatMode
import com.mdunggggg.jamendo_music.player.service.MusicService
import com.mindy.jamendo_core_data.model.Track
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MusicControllerManagement @Inject constructor(
    @ApplicationContext private val context: Context
) {
    private var mediaController: MediaController? = null

    private val _musicState = MutableStateFlow(MusicState())
    val musicState = _musicState.asStateFlow()

    private var currentPlaylist: List<Track> = emptyList()

    private val handler = Handler(Looper.getMainLooper())
    private val progressUpdateRunnable = object : Runnable {
        override fun run() {
            updateProgress()
            handler.postDelayed(this, PROGRESS_UPDATE_INTERVAL)
        }
    }

    fun connect() {
        if (mediaController != null) return

        val sessionToken = SessionToken(context, ComponentName(context, MusicService::class.java))
        val controllerFuture = MediaController.Builder(context, sessionToken).buildAsync()
        controllerFuture.addListener({
            mediaController = controllerFuture.get()
            setupPlayerListener()
            syncStateFromPlayer()
        }, MoreExecutors.directExecutor())
    }

    private fun setupPlayerListener() {
        mediaController?.addListener(object : Player.Listener {
            override fun onIsPlayingChanged(isPlaying: Boolean) {
                _musicState.update { it.copy(isPlaying = isPlaying) }

                // Start/stop progress updates
                if (isPlaying) {
                    startProgressUpdates()
                } else {
                    stopProgressUpdates()
                }
            }

            override fun onPlaybackStateChanged(state: Int) {
                val playbackState = when (state) {
                    Player.STATE_IDLE -> PlaybackState.IDLE
                    Player.STATE_BUFFERING -> PlaybackState.BUFFERING
                    Player.STATE_READY -> PlaybackState.READY
                    Player.STATE_ENDED -> PlaybackState.ENDED
                    else -> PlaybackState.IDLE
                }
                _musicState.update { it.copy(playbackState = playbackState) }

                if (state == Player.STATE_READY) {
                    val duration = mediaController?.duration ?: 0L
                    _musicState.update { it.copy(duration = duration) }
                }
            }

            override fun onMediaItemTransition(mediaItem: MediaItem?, reason: Int) {
                updateCurrentTrack()
            }

            override fun onShuffleModeEnabledChanged(shuffleModeEnabled: Boolean) {
                _musicState.update { it.copy(isShuffleEnabled = shuffleModeEnabled) }
            }

            override fun onRepeatModeChanged(repeatMode: Int) {
                val mode = when (repeatMode) {
                    Player.REPEAT_MODE_OFF -> RepeatMode.OFF
                    Player.REPEAT_MODE_ONE -> RepeatMode.ONE
                    Player.REPEAT_MODE_ALL -> RepeatMode.ALL
                    else -> RepeatMode.OFF
                }
                _musicState.update { it.copy(repeatMode = mode) }
            }

            override fun onPlayerError(error: PlaybackException) {
                _musicState.update { it.copy(playbackState = PlaybackState.ERROR) }
            }
        })
    }


    private fun syncStateFromPlayer() {
        mediaController?.let { controller ->
            val isPlaying = controller.isPlaying
            val duration = controller.duration.takeIf { it > 0 } ?: 0L
            val progress = controller.currentPosition
            val shuffleEnabled = controller.shuffleModeEnabled
            val repeatMode = when (controller.repeatMode) {
                Player.REPEAT_MODE_OFF -> RepeatMode.OFF
                Player.REPEAT_MODE_ONE -> RepeatMode.ONE
                Player.REPEAT_MODE_ALL -> RepeatMode.ALL
                else -> RepeatMode.OFF
            }
            val playbackState = when (controller.playbackState) {
                Player.STATE_IDLE -> PlaybackState.IDLE
                Player.STATE_BUFFERING -> PlaybackState.BUFFERING
                Player.STATE_READY -> PlaybackState.READY
                Player.STATE_ENDED -> PlaybackState.ENDED
                else -> PlaybackState.IDLE
            }

            _musicState.update {
                it.copy(
                    isPlaying = isPlaying,
                    duration = duration,
                    progress = progress,
                    isShuffleEnabled = shuffleEnabled,
                    repeatMode = repeatMode,
                    playbackState = playbackState
                )
            }

            if (isPlaying) {
                startProgressUpdates()
            }
        }
    }

    private fun updateCurrentTrack() {
        val controller = mediaController ?: return
        val index = controller.currentMediaItemIndex

        if (currentPlaylist.isNotEmpty() && index in currentPlaylist.indices) {
            val track = currentPlaylist[index]
            val duration = controller.duration.takeIf { it > 0 } ?: 0L

            _musicState.update {
                it.copy(
                    currentTrack = track,
                    currentIndex = index,
                    duration = duration,
                    progress = 0L
                )
            }
        }
    }

    private fun updateProgress() {
        mediaController?.let { controller ->
            val position = controller.currentPosition
            val duration = controller.duration.takeIf { it > 0 } ?: 0L

            _musicState.update {
                it.copy(
                    progress = position,
                    duration = duration
                )
            }
        }
    }

    private fun startProgressUpdates() {
        handler.removeCallbacks(progressUpdateRunnable)
        handler.post(progressUpdateRunnable)
    }

    private fun stopProgressUpdates() {
        handler.removeCallbacks(progressUpdateRunnable)
    }

    fun play(track: Track) {
        currentPlaylist = listOf(track)

        val mediaItem = track.toMediaItem()
        mediaController?.setMediaItem(mediaItem)
        mediaController?.prepare()
        mediaController?.play()

        _musicState.update {
            it.copy(
                currentTrack = track,
                playlist = currentPlaylist,
                currentIndex = 0,
                progress = 0L,
                duration = 0L
            )
        }
    }

    fun pause() {
        mediaController?.pause()
    }

    fun resume() {
        mediaController?.play()
    }

    fun next() {
        mediaController?.seekToNext()
    }

    fun previous() {
        mediaController?.seekToPrevious()
    }

    fun seekTo(positionMs: Long) {
        mediaController?.seekTo(positionMs)
        _musicState.update { it.copy(progress = positionMs) }
    }

    fun setPlaylist(tracks: List<Track>, startIndex: Int = 0) {
        if (tracks.isEmpty()) return

        currentPlaylist = tracks

        val mediaItems = tracks.map { it.toMediaItem() }
        mediaController?.setMediaItems(mediaItems, startIndex, 0L)
        mediaController?.prepare()
        mediaController?.play()

        _musicState.update {
            it.copy(
                currentTrack = tracks[startIndex],
                playlist = tracks,
                currentIndex = startIndex,
                progress = 0L,
                duration = 0L
            )
        }
    }

    fun toggleShuffle() {
        mediaController?.let { controller ->
            controller.shuffleModeEnabled = !controller.shuffleModeEnabled
        }
    }

    fun cycleRepeatMode() {
        mediaController?.let { controller ->
            controller.repeatMode = when (controller.repeatMode) {
                Player.REPEAT_MODE_OFF -> Player.REPEAT_MODE_ALL
                Player.REPEAT_MODE_ALL -> Player.REPEAT_MODE_ONE
                Player.REPEAT_MODE_ONE -> Player.REPEAT_MODE_OFF
                else -> Player.REPEAT_MODE_OFF
            }
        }
    }

    fun disconnect() {
        stopProgressUpdates()
        mediaController?.release()
        mediaController = null
    }

    companion object {
        private const val PROGRESS_UPDATE_INTERVAL = 1000L
    }
}

private fun Track.toMediaItem(): MediaItem {
    return MediaItem.Builder()
        .setUri(audio)
        .setMediaId(id)
        .setMediaMetadata(
            MediaMetadata.Builder()
                .setTitle(name)
                .setTrackNumber(position)
                .build()
        )
        .build()
}