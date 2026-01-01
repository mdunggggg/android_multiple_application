package com.mdunggggg.jamendo_music.screen.home.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mdunggggg.jamendo_music.screen.ext.toHeight
import com.mdunggggg.jamendo_music.ui.theme.JamendoTypography
import com.mindy.jamendo_core_data.model.Radio

@Composable
fun RadioSection(modifier: Modifier = Modifier, radios : List<Radio>) {
    Column(modifier = modifier.fillMaxWidth().padding(horizontal = 16.dp)) {
        Text(
            text = "Live Radios",
            color = Color.White,
            style = JamendoTypography.boldTextStyle.copy(fontSize = 18.sp)
        )
        4.toHeight()
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            radios.forEach { radio ->
                item {
                    RadioItem(
                        url = radio.image,
                        title = radio.dispname
                    )
                }
            }
        }
    }
}