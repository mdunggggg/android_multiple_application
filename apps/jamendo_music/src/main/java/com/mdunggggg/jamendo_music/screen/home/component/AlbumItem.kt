package com.mdunggggg.jamendo_music.screen.home.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import coil3.request.error
import coil3.request.fallback
import com.mdunggggg.jamendo_music.screen.ext.toHeight
import com.mdunggggg.jamendo_music.ui.theme.JamendoTypography

@Composable
fun AlbumItem(url: String, title: String, artist: String) {
    Column(
        horizontalAlignment = Alignment.Start
    ) {
        val expandedDropDownButton = remember {
            mutableStateOf(false)
        }
        val items = listOf("Add to Playlist", "Share", "Go to Artist")
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(url)
                .crossfade(true)
                .error(com.mdunggggg.jamendo_music.R.drawable.outline_error_24)
                .fallback(com.mdunggggg.jamendo_music.R.drawable.outline_error_24)
                .build(),
            contentDescription = title,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(16.dp))
                .background(Color.Gray),
            contentScale = ContentScale.Crop
        )
        4.toHeight()
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = title,
                    style = JamendoTypography.boldTextStyle.copy(fontSize = 14.sp),
                    color = Color.White,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                2.toHeight()
                Text(
                    text = artist,
                    style = JamendoTypography.regularTextStyle.copy(fontSize = 12.sp),
                    color = Color(0xFF64748B),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            Box {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = null,
                    tint = Color(0xFF64748B),
                    modifier = Modifier.clickable {
                        expandedDropDownButton.value = true
                    }
                )

                DropdownMenu(
                    expanded = expandedDropDownButton.value,
                    onDismissRequest = { expandedDropDownButton.value = false }
                ) {
                    items.forEach { item ->
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = item,
                                    style = JamendoTypography.regularTextStyle.copy(fontSize = 14.sp),
                                    color = Color.Black
                                )
                            },
                            onClick = {
                                expandedDropDownButton.value = false
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun AlbumItemPrev() {
    AlbumItem(
        url = "https://imgjamendo.com/jamendo2010/albums/s100/1/118/118192.jpg",
        title = "Album Title Example That Is Quite Long",
        artist = "Artist Name"
    )
}