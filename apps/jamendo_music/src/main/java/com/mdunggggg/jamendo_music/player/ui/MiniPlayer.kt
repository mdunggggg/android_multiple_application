package com.mdunggggg.jamendo_music.player.ui

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.mdunggggg.jamendo_music.dummy.Dummy
import com.mdunggggg.jamendo_music.player.MusicState
import com.mdunggggg.jamendo_music.player.controller.MusicControllerManagement
import com.mindy.jamendo_core_data.model.Track


@Composable
fun MiniPlayer(
    musicState: MusicState,
    onPlayPauseClick: () -> Unit = {},
    onMiniPlayerClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    val currentTrack = musicState.currentTrack ?: return
    val rotationDegrees = musicState.progress * 360f / (musicState.duration.takeIf { it > 0 } ?: 1L)

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp)
            .clickable { onMiniPlayerClick() },
        color = Color(0xFF1A1A2E),
        tonalElevation = 8.dp
    ) {
        Column {
            LinearProgressIndicator(
                progress = {
                    if (musicState.duration > 0) {
                        musicState.progress.toFloat() / musicState.duration.toFloat()
                    } else 0f
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp),
                color = Color(0xFF2DD4BF),
                trackColor = Color(0xFF374151)
            )

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 12.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Dummy.dummyAvatar(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .rotate(rotationDegrees)
                )

                Spacer(modifier = Modifier.width(12.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = currentTrack.name,
                        style =
                            MaterialTheme.typography.bodyMedium,
                        color = Color.White,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = "Unknown Artist",
                        style = MaterialTheme.typography.bodySmall,
                        color = Color(0xFF9CA3AF),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }

                IconButton(onClick = onPlayPauseClick) {
                    Icon(
                        imageVector = if (musicState.isPlaying)
                            Icons.Filled.Done
                        else
                            Icons.Filled.PlayArrow,
                        contentDescription = if (musicState.isPlaying) "Pause" else "Play",
                        tint = Color.White,
                        modifier = Modifier.size(32.dp)
                    )
                }
            }
        }
    }
}


@Preview(showSystemUi = true)
@Composable
private fun MiniPlayerProgressPrv() {
    MiniPlayer(
        musicState = MusicState(
            progress = 30000L,
            duration = 120000L,
            isPlaying = true,
            currentTrack = Dummy.dummyTracks.first(),
        )
    )
}
