package com.mdunggggg.jamendo_music.screen.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.mdunggggg.jamendo_music.screen.home.component.HeaderItem

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
        .background(color = Color(0xFF020408))
        .fillMaxSize(),
) {
    Box(modifier = modifier) {
        Column {
            HeaderItem(title = "I'm Dungggg", countNotification = 4)
        }
    }
}


@Preview(showSystemUi = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}