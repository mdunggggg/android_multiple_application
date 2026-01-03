package com.mdunggggg.jamendo_music.screen.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.mdunggggg.jamendo_music.screen.ext.toHeight
import com.mdunggggg.jamendo_music.screen.ext.toWidth
import com.mdunggggg.jamendo_music.ui.theme.JamendoTypography

@Composable
fun HeaderItem(
    modifier: Modifier = Modifier
        .background(color = Color(0xFF020408))
        .padding(16.dp)
        .fillMaxWidth(),
    title: String,
    countNotification: Int
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = "Welcome back",
                color = Color(0xFF64748B),
                style = JamendoTypography.mediumTextStyle
            )
            3.toHeight()
            Text(
                text = title,
                style = JamendoTypography.boldTextStyle.copy(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xFF2DD4BF),
                            Color(0xFFF97316)
                        )
                    )
                )
            )
        }
        BadgedBox(
            badge = {
                if (countNotification > 0) {
                    Badge(
                        containerColor = Color.Red,
                        contentColor = Color.White
                    ) {
                        Text(countNotification.toString())
                    }
                }
            }
        ) {
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier.size(32.dp)
            )
        }
        16.toWidth()
        AsyncImage(
            model = "https://scontent.fhan5-11.fna.fbcdn.net/v/t39.30808-1/470194678_2070287133410355_5243510936342359981_n.jpg?stp=dst-jpg_s480x480_tt6&_nc_cat=100&ccb=1-7&_nc_sid=1d2534&_nc_ohc=e9MA-bahipwQ7kNvwEsEgFi&_nc_oc=Adm0Tmr2ivfAXXCLfq1sRgWlJauh4WXyyNY8tWvBUYY5ZTR08OevBqfi0-EiNWLALcv70OtSHeG-c9EOq74HfUCY&_nc_zt=24&_nc_ht=scontent.fhan5-11.fna&_nc_gid=FdImc8h7Z2Xp-UtWRH8MWQ&oh=00_Afq1abmmpWg0pjjLyyjPaZe1UqBq6O6_diNerJs-5MSglA&oe=695C48E2",
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop
        )
    }
}


@Preview()
@Composable
fun HeaderItemPreview(modifier: Modifier = Modifier) {
    HeaderItem(title = "New Releases", countNotification = 3)
}

