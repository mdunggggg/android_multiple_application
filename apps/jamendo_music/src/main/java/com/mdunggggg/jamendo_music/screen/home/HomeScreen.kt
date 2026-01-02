package com.mdunggggg.jamendo_music.screen.home

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.mdunggggg.core_ui.BaseScreen
import com.mdunggggg.jamendo_music.screen.ext.toHeight
import com.mdunggggg.jamendo_music.screen.home.component.AlbumItem
import com.mdunggggg.jamendo_music.screen.home.component.HeaderItem
import com.mdunggggg.jamendo_music.screen.home.component.TrendingSection
import com.mdunggggg.jamendo_music.screen.home.component.RadioSection
import com.mdunggggg.jamendo_music.screen.home.component.RecommendationSection
import com.mdunggggg.jamendo_music.screen.home.data.trendingDataDummys
import com.mdunggggg.jamendo_music.ui.theme.JamendoTypography

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onDetailAlbumClick : (String) -> Unit = {}
) {
    BaseScreen(
        viewModel = viewModel
    ) { data ->
        Box(
            modifier = Modifier
                .background(color = Color(0xFF020408))
                .fillMaxSize()
        ) {

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                contentPadding = PaddingValues(bottom = 16.dp)
            ) {
                item(span = { GridItemSpan(maxLineSpan) }) {
                    HeaderItem(title = "I'm Dungggg", countNotification = 4)
                }

                item(span = { GridItemSpan(maxLineSpan) }) {
                    TrendingSection(trendingList = trendingDataDummys)
                }

                if (data.radios.isNotEmpty()) {
                    item(span = { GridItemSpan(maxLineSpan) }) {
                        RadioSection(radios = data.radios)
                    }
                }

                if (data.albums.isNotEmpty()) {
                    item(span = { GridItemSpan(maxLineSpan) }) {
                        Text(
                            text = "Recommended for You",
                            color = Color.White,
                            style = JamendoTypography.boldTextStyle.copy(fontSize = 18.sp),
                            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                        )
                    }
                    items(data.albums.size) { index ->
                        val album = data.albums[index]
                        Box(modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .clickable {
                                onDetailAlbumClick(album.id)
                            }) {
                            AlbumItem(
                                url = album.image,
                                title = album.name,
                                artist = album.artistName
                            )
                        }
                    }
                }
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}