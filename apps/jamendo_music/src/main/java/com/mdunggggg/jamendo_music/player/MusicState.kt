package com.mdunggggg.jamendo_music.player

import com.mindy.jamendo_core_data.model.Track

data class MusicState(
    val playbackState: PlaybackState = PlaybackState.IDLE,
    val currentTrack: Track? = null,
    val playlist: List<Track> = emptyList(),
    val currentIndex: Int = 0,
    val progress: Long = 0L,
    val duration: Long = 0L,
    val isPlaying: Boolean = false,
    val isShuffleEnabled: Boolean = false,
    val repeatMode: RepeatMode = RepeatMode.OFF
)

enum class PlaybackState {
    IDLE,
    BUFFERING,
    READY,
    ENDED,
    ERROR
}

enum class RepeatMode {
    OFF,
    ONE,
    ALL
}