package com.mdunggggg.jamendo_music.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mdunggggg.core_ui.BaseScreen
import com.mdunggggg.jamendo_music.screen.home.component.HeaderItem
import com.mdunggggg.jamendo_music.screen.home.component.TrendingSection
import com.mdunggggg.jamendo_music.screen.home.HomeViewModel
import com.mdunggggg.jamendo_music.screen.home.component.RadioSection
import com.mdunggggg.jamendo_music.screen.home.data.trendingDataDummys

@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel()
) {
    BaseScreen(
        viewModel = viewModel
    ) { data ->
        val radios by remember(data.radios) {
            mutableStateOf(data.radios)
        }
        Box(
            modifier = Modifier
                .background(color = Color(0xFF020408))
                .fillMaxSize()
        ) {
            Column {
                HeaderItem(title = "I'm Dungggg", countNotification = 4)
                TrendingSection(trendingList = trendingDataDummys)

                if (radios.isNotEmpty()) {
                    RadioSection(
                        radios = radios
                    )
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