package com.mdunggggg.jamendo_music.player.controller

import android.content.ComponentName
import android.content.Context
import androidx.media3.common.MediaItem
import androidx.media3.common.MediaMetadata
import androidx.media3.common.Player
import androidx.media3.session.MediaController
import androidx.media3.session.SessionToken
import com.google.common.util.concurrent.MoreExecutors
import com.mdunggggg.jamendo_music.player.MusicState
import com.mdunggggg.jamendo_music.player.PlaybackState
import com.mdunggggg.jamendo_music.player.service.MusicService
import com.mindy.jamendo_core_data.model.Track
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MusicControllerManagement @Inject constructor(
    @ApplicationContext private val context: Context
){
    private var mediaController: MediaController? = null

    private val _musicState = MutableStateFlow(MusicState())
    val musicState = _musicState.asStateFlow()

    private var job: Job? = null

    fun connect() {
        val sessionToken = SessionToken(context, ComponentName(context, MusicService::class.java))
        val controllerFuture = MediaController.Builder(context, sessionToken).buildAsync()
        controllerFuture.addListener({
            mediaController = controllerFuture.get()
            setupPlayerListener()
        }, MoreExecutors.directExecutor())
    }

    private fun setupPlayerListener() {
        mediaController?.addListener(object : Player.Listener {
            override fun onIsPlayingChanged(isPlaying: Boolean) {
                _musicState.update { it.copy(isPlaying = isPlaying) }
                if (isPlaying) {
                    job?.cancel()
                    job = getProgressUpdaterJob()
                } else {
                    job?.cancel()
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
            }
        })
    }

    private fun getProgressUpdaterJob(): Job {
        return CoroutineScope(Dispatchers.Main + SupervisorJob()).launch {
            while (true) {
                val duration = mediaController?.currentPosition ?: 0L
                _musicState.update { it.copy(progress = duration) }
                delay(1000L)
            }
        }
    }


    fun play(track: Track) {
        _musicState.update {
            MusicState(
               currentTrack = track,
                playlist = listOf(track)
            )
        }
        mediaController?.setMediaItem(MediaItem.fromUri(track.audio))
        mediaController?.prepare()
        mediaController?.play()
    }

    fun setPlaylist(tracks: List<Track>, startIndex: Int = 0) {
        val mediaItems = tracks.map {
            MediaItem.fromUri(it.audio)
        }
        mediaController?.setMediaItems(mediaItems, startIndex, 0L)
        mediaController?.prepare()
        mediaController?.play()
    }

    fun pause() = mediaController?.pause()

    fun resume() = mediaController?.play()

    fun next() = mediaController?.seekToNext()

    fun previous() = mediaController?.seekToPrevious()

    fun seekTo(positionMs: Long) = mediaController?.seekTo(positionMs)


    fun disconnect() {
        mediaController?.release()
        mediaController = null
        job?.cancel()
        job = null
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