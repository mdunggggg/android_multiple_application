package com.mdunggggg.jamendo_music.screen.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mdunggggg.jamendo_music.screen.home.data.TrendingData
import com.mdunggggg.jamendo_music.ui.theme.JamendoTypography

@Composable
fun TrendingSection(
    modifier: Modifier = Modifier
        .background(color = Color(0xFF020408))
        .padding(16.dp)
        .fillMaxWidth(),
    trendingList: List<TrendingData> = emptyList()
) {
    val scrollState = rememberScrollState()
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Trending now",
                color = Color.White,
                style = JamendoTypography.boldTextStyle.copy(fontSize = 18.sp)
            )
            Text(
                text = "See all",
                color = Color(0xFF2DD4BF),
                style = JamendoTypography.semiboldTextStyle
            )
        }
        Row(
            modifier = modifier
                .horizontalScroll(scrollState),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            for (trending in trendingList) {
                TrendingCard(
                    url = trending.thumbnailUrl,
                    trendingType = trending.trendingType,
                    trendingTitle = trending.trendingTitle,
                    trendingDescription = trending.trendingDescription
                )
            }
        }
    }
}

@Preview
@Composable
private fun TrendingSectionPrev() {
    TrendingSection()
}