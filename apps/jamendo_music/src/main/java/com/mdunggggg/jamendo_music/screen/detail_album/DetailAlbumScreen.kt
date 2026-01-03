package com.mdunggggg.jamendo_music.screen.detail_album

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.mdunggggg.core_ui.BaseScreen
import com.mindy.jamendo_core_data.model.Track

@Composable
fun DetailAlbumScreen(
    idAlbum: String,
    viewModel: DetailAlbumViewModel = hiltViewModel(),
    onTrackPlay: (List<Track>, Int) -> Unit,
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
        DetailAlbumContent(album = data.album, onTrackPlay = { position ->
            onTrackPlay(data.album.tracks, position)
        })
    }
}