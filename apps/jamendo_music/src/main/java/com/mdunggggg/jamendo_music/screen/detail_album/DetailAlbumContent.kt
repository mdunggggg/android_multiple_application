package com.mdunggggg.jamendo_music.screen.detail_album

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
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
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.mdunggggg.jamendo_music.dummy.Dummy
import com.mdunggggg.jamendo_music.screen.ext.toHeight
import com.mdunggggg.jamendo_music.screen.ext.toWidth
import com.mdunggggg.jamendo_music.ui.theme.JamendoTypography
import com.mindy.jamendo_core_data.model.Album

@Composable
fun DetailAlbumContent(modifier: Modifier = Modifier, album : Album) {
    Box(
        modifier = Modifier
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF0860A4),
                        Color(0xFF000000)
                    )
                )
            )
            .padding(16.dp)
            .fillMaxSize()
    ) {
        Icon(
            imageVector = Icons.Default.KeyboardArrowLeft,
            contentDescription = "Back",
            tint = Color.White,
            modifier = Modifier.size(32.dp)
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            item {
                Box(
                    modifier = Modifier.fillMaxWidth().padding(top = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    AsyncImage(
                        model = album.image,
                        contentDescription = "Album Art",
                        modifier = Modifier
                            .size(300.dp)
                            .clip(RoundedCornerShape(4.dp)),
                        contentScale = ContentScale.Fit
                    )
                }
            }
            item {
                Text(
                    album.name,
                    color = Color.White,
                    style = JamendoTypography.boldTextStyle.copy(fontSize = 24.sp)
                )
            }
            item {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    AsyncImage(
                        model = "https://scontent.fhan5-11.fna.fbcdn.net/v/t39.30808-1/470194678_2070287133410355_5243510936342359981_n.jpg?stp=dst-jpg_s480x480_tt6&_nc_cat=100&ccb=1-7&_nc_sid=1d2534&_nc_ohc=e9MA-bahipwQ7kNvwEsEgFi&_nc_oc=Adm0Tmr2ivfAXXCLfq1sRgWlJauh4WXyyNY8tWvBUYY5ZTR08OevBqfi0-EiNWLALcv70OtSHeG-c9EOq74HfUCY&_nc_zt=24&_nc_ht=scontent.fhan5-11.fna&_nc_gid=FdImc8h7Z2Xp-UtWRH8MWQ&oh=00_Afq1abmmpWg0pjjLyyjPaZe1UqBq6O6_diNerJs-5MSglA&oe=695C48E2",
                        contentDescription = null,
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape),
                        contentScale = ContentScale.Crop
                    )
                    4.toWidth()
                    Text(
                        text = album.artistName,
                        color = Color.White,
                        style = JamendoTypography.mediumTextStyle
                    )
                }
            }

            album.tracks.map {
                item {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                            .padding(bottom = 12.dp)
                    ) {
                        Text(
                            text = it.name,
                            color = Color.White,
                            style = JamendoTypography.regularTextStyle
                        )
                        4.toHeight()
                        Text(
                            text = "Duration: ${it.duration} seconds",
                            color = Color(0xFF64748B),
                            style = JamendoTypography.regularTextStyle.copy(fontSize = 12.sp)
                        )
                    }
                }
            }
        }
    }

}

@Preview(showSystemUi = true)
@Composable
private fun DetailAlbumPv() {
    DetailAlbumContent(
        album = Dummy.dummyAlbum
    )
}