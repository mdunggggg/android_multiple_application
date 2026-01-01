package com.mdunggggg.jamendo_music.screen.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.mdunggggg.jamendo_music.screen.ext.toHeight
import com.mdunggggg.jamendo_music.ui.theme.JamendoTypography

@Composable
fun TrendingCard(
    modifier: Modifier = Modifier
        .width(300.dp)
        .height(200.dp)
        .clip(RoundedCornerShape(32.dp)),
    url: String,
    trendingType : String,
    trendingTitle : String,
    trendingDescription : String,
) {
    Box(modifier = modifier) {
        AsyncImage(
            model = url,
            contentDescription = null,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    brush = Brush.verticalGradient(
                        colors = listOf(Color.Transparent, Color(0xFF000000)),
                        startY = 300f,
                    )
                )
        )
        Column(
            verticalArrangement = Arrangement.Bottom,
            modifier = Modifier.fillMaxHeight().padding(16.dp)
        ) {
            ChipCustom(
                title = trendingType,
                backgroundColor = Color(0xFFF97316)
            )
            8.toHeight()
            Text(
                text = trendingTitle,
                color = Color.White,
                style = JamendoTypography.boldTextStyle.copy(fontSize = 20.sp)
            )
            4.toHeight()
            Text(
                text = trendingDescription,
                color = Color(0xFFD1D5DC),
                style = JamendoTypography.mediumTextStyle
            )
        }
    }
}

@Preview
@Composable
private fun TrendingCardPrev() {
    TrendingCard(url = "", trendingType = "Playlist", trendingTitle = "Top Hits", trendingDescription = "The hottest tracks right now")
}