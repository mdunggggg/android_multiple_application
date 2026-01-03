package com.mdunggggg.jamendo_music.route

import androidx.navigation3.runtime.NavKey
import com.mdunggggg.jamendo_music.player.MusicState
import kotlinx.serialization.Serializable

@Serializable
sealed interface JamendoRoute : NavKey {

    @Serializable
    data object Home : JamendoRoute

    @Serializable
    data object Search : JamendoRoute

    @Serializable
    data object Library : JamendoRoute

    @Serializable
    data object Profile : JamendoRoute

    @Serializable
    data class DetailAlbum(
        val id : String,
    ) : JamendoRoute

    @Serializable
    data class PlayTrack(
        val musicState: MusicState
    ) : JamendoRoute
}