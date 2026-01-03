package com.mdunggggg.jamendo_music.screen.detail_album

import android.util.Log
import com.mdunggggg.core_ui.BaseViewModel
import com.mindy.jamendo_core_data.model.Album
import com.mindy.jamendo_core_data.repository.JamendoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class DetailAlbumData(
    val album : Album? = null
)

@HiltViewModel
class DetailAlbumViewModel @Inject constructor(
    private val jamendoRepository: JamendoRepository
) : BaseViewModel<DetailAlbumData>(DetailAlbumData()) {
    fun fetchAlbum(idAlbum : String) {
        safelyLaunch {
            val album = jamendoRepository.fetchAlbum(idAlbum).getOrNull()
            currentData.copy(album = album)
        }
    }
}