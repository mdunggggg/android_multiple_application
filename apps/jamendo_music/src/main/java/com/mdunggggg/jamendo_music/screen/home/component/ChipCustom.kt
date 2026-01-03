package com.mdunggggg.jamendo_music.screen.home.component

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mdunggggg.jamendo_music.ui.theme.JamendoTypography

@Composable
fun ChipCustom(
    modifier: Modifier = Modifier,
    title: String,
    titleStyle: TextStyle = JamendoTypography.boldTextStyle.copy(fontSize = 10.sp),
    backgroundColor: Color
) {
    Surface(
        color = backgroundColor,
        shape = CircleShape,
        modifier = modifier
    ) {
        Text(
            title,
            style = titleStyle,
            color = Color.White,
            modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
        )
    }
}

@Preview
@Composable
private fun ChipCustom() {
    ChipCustom(title = "LIVE EVENT", backgroundColor = Color.Red)
}