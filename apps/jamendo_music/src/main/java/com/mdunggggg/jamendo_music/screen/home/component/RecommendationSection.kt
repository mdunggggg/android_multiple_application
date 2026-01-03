package com.mdunggggg.jamendo_music.screen.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mdunggggg.jamendo_music.screen.ext.toHeight
import com.mdunggggg.jamendo_music.ui.theme.JamendoTypography
import com.mindy.jamendo_core_data.model.Album

@Composable
fun RecommendationSection(modifier: Modifier = Modifier, albums : List<Album>) {
    Column(modifier = modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
        Text(
            text = "Recommended for You",
            color = Color.White,
            style = JamendoTypography.boldTextStyle.copy(fontSize = 18.sp)
        )
        4.toHeight()
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            userScrollEnabled = false,
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(albums.size) { index ->
                val album = albums[index]
                AlbumItem(
                    url = album.image,
                    title = album.name,
                    artist = album.artistName
                )
            }
        }
    }
}