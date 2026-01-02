package com.mdunggggg.jamendo_music.screen.detail_album

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.mdunggggg.core_ui.BaseScreen

@Composable
fun DetailAlbumScreen(
    idAlbum : String,
    viewModel : DetailAlbumViewModel = hiltViewModel()
) {
    LaunchedEffect(idAlbum) {
        viewModel.fetchAlbum(idAlbum)
    }
    BaseScreen(
        viewModel = viewModel
    ) { data ->

        if (data.album == null) {
            return@BaseScreen
        }
        DetailAlbumContent(album = data.album)
    }
}