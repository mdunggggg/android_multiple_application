package com.mdunggggg.jamendo_music.screen.play_track

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode as AnimationRepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.mdunggggg.jamendo_music.R
import com.mdunggggg.jamendo_music.dummy.Dummy
import com.mdunggggg.jamendo_music.player.MusicState
import com.mdunggggg.jamendo_music.player.RepeatMode
import com.mindy.jamendo_core_data.model.Track

private val BackgroundColor = Color(0xFF0D0D14)
private val SurfaceColor = Color(0xFF1A1A2E)
private val PrimaryAccent = Color(0xFF2DD4BF)
private val SecondaryAccent = Color(0xFFF97316)
private val TextPrimary = Color.White
private val TextSecondary = Color(0xFF9CA3AF)
private val TextMuted = Color(0xFF64748B)
private val VinylBorderColor = Color(0xFF6B7280)

@Composable
fun PlayTrackScreen(
    modifier: Modifier = Modifier,
    musicState: MusicState,
    onBackClick: () -> Unit = {},
    onMoreClick: () -> Unit = {},
    onShuffleClick: () -> Unit = {},
    onPreviousClick: () -> Unit = {},
    onPlayPauseClick: () -> Unit = {},
    onNextClick: () -> Unit = {},
    onRepeatClick: () -> Unit = {},
    onSeek: (Long) -> Unit = {},
    onDevicesClick: () -> Unit = {},
    onShareClick: () -> Unit = {},
    onLikedClick: () -> Unit = {},
    onSimilarTrackClick: (Track) -> Unit = {},
    onViewQueueClick: () -> Unit = {},
) {
    val currentTrack = musicState.currentTrack ?: return

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(BackgroundColor)
            .verticalScroll(rememberScrollState())
    ) {
        TopBar(
            albumName = "Midnight Beats Vol. 4",
            onBackClick = onBackClick,
            onMoreClick = onMoreClick
        )

        Spacer(modifier = Modifier.height(16.dp))

        VinylAlbumArt(
            imageUrl = Dummy.dummyAlbum.image,
            isPlaying = musicState.isPlaying,
            modifier = Modifier
                .padding(horizontal = 48.dp)
                .fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))

        TrackInfo(
            trackName = currentTrack.name,
            artistName = Dummy.dummyAlbum.artistName,
            albumName = "Midnight Beats Vol. 4"
        )

        Spacer(modifier = Modifier.height(24.dp))

        ProgressSection(
            progress = musicState.progress,
            duration = musicState.duration,
            onSeek = onSeek,
            modifier = Modifier.padding(horizontal = 24.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        PlayerControls(
            isPlaying = musicState.isPlaying,
            isShuffleEnabled = musicState.isShuffleEnabled,
            repeatMode = musicState.repeatMode,
            onShuffleClick = onShuffleClick,
            onPreviousClick = onPreviousClick,
            onPlayPauseClick = onPlayPauseClick,
            onNextClick = onNextClick,
            onRepeatClick = onRepeatClick,
            modifier = Modifier.padding(horizontal = 24.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        TechnicalInfoSection(
            format = "FLAC",
            bitrate = "1,411 kbps",
            license = getLicenseShortName(currentTrack.licenseCcUrl),
            modifier = Modifier.padding(horizontal = 24.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        SimilarTracksSection(
            tracks = musicState.playlist.filter { it.id != currentTrack.id },
            onTrackClick = onSimilarTrackClick,
            onViewQueueClick = onViewQueueClick
        )

        Spacer(modifier = Modifier.height(24.dp))

        BottomActionBar(
            onDevicesClick = onDevicesClick,
            onShareClick = onShareClick,
            onLikedClick = onLikedClick,
            modifier = Modifier.padding(horizontal = 48.dp, vertical = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))
    }
}

@Composable
private fun TopBar(
    albumName: String,
    onBackClick: () -> Unit,
    onMoreClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp, vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onBackClick) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                tint = TextPrimary
            )
        }

        Column(
            modifier = Modifier.weight(1f),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "NOW PLAYING",
                style = MaterialTheme.typography.labelSmall,
                color = PrimaryAccent,
                letterSpacing = 2.sp
            )
            Text(
                text = albumName,
                style = MaterialTheme.typography.bodyMedium,
                color = TextPrimary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

        IconButton(onClick = onMoreClick) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "More options",
                tint = TextPrimary
            )
        }
    }
}

@Composable
private fun VinylAlbumArt(
    imageUrl: String,
    isPlaying: Boolean,
    modifier: Modifier = Modifier
) {
    val infiniteTransition = rememberInfiniteTransition(label = "vinyl_rotation")
    val rotation by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 360f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = 8000, easing = LinearEasing),
            repeatMode = AnimationRepeatMode.Restart
        ),
        label = "rotation"
    )

    val currentRotation = if (isPlaying) rotation else 0f

    Box(
        modifier = modifier.aspectRatio(1f),
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .rotate(currentRotation)
                .clip(CircleShape)
                .background(
                    Brush.radialGradient(
                        colors = listOf(
                            Color(0xFF4A4A4A),
                            Color(0xFF2A2A2A),
                            Color(0xFF1A1A1A)
                        )
                    )
                )
                .border(4.dp, VinylBorderColor, CircleShape)
        )

        AsyncImage(
            model = imageUrl,
            contentDescription = "Album Art",
            modifier = Modifier
                .fillMaxSize(0.65f)
                .rotate(currentRotation)
                .clip(CircleShape)
                .border(3.dp, VinylBorderColor.copy(alpha = 0.5f), CircleShape),
            contentScale = ContentScale.Crop
        )

        Box(
            modifier = Modifier
                .size(16.dp)
                .clip(CircleShape)
                .background(BackgroundColor)
                .border(2.dp, VinylBorderColor.copy(alpha = 0.3f), CircleShape)
        )
    }
}

@Composable
private fun TrackInfo(
    trackName: String,
    artistName: String,
    albumName: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val words = trackName.split(" ")
        val styledText = buildAnnotatedString {
            if (words.isNotEmpty()) {
                withStyle(SpanStyle(fontWeight = FontWeight.Bold)) {
                    append(words.first())
                }
                if (words.size > 1) {
                    append(" ")
                    withStyle(SpanStyle(fontWeight = FontWeight.Light)) {
                        append(words.drop(1).joinToString(" "))
                    }
                }
            }
        }

        Text(
            text = styledText,
            style = MaterialTheme.typography.headlineLarge,
            color = TextPrimary,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = artistName,
            style = MaterialTheme.typography.titleMedium,
            color = PrimaryAccent,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = albumName,
            style = MaterialTheme.typography.bodyMedium,
            color = TextSecondary,
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun ProgressSection(
    progress: Long,
    duration: Long,
    onSeek: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Slider(
            value = if (duration > 0) progress.toFloat() / duration.toFloat() else 0f,
            onValueChange = { value ->
                onSeek((value * duration).toLong())
            },
            colors = SliderDefaults.colors(
                thumbColor = PrimaryAccent,
                activeTrackColor = PrimaryAccent,
                inactiveTrackColor = TextMuted.copy(alpha = 0.3f)
            ),
            modifier = Modifier.fillMaxWidth()
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = formatDuration(progress),
                style = MaterialTheme.typography.bodySmall,
                color = PrimaryAccent
            )
            Text(
                text = formatDuration(duration),
                style = MaterialTheme.typography.bodySmall,
                color = TextSecondary
            )
        }
    }
}

@Composable
private fun PlayerControls(
    isPlaying: Boolean,
    isShuffleEnabled: Boolean,
    repeatMode: RepeatMode,
    onShuffleClick: () -> Unit,
    onPreviousClick: () -> Unit,
    onPlayPauseClick: () -> Unit,
    onNextClick: () -> Unit,
    onRepeatClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(onClick = onShuffleClick) {
            Icon(
                painter = painterResource(id = R.drawable.ic_shuffle),
                contentDescription = "Shuffle",
                tint = if (isShuffleEnabled) PrimaryAccent else TextSecondary,
                modifier = Modifier.size(24.dp)
            )
        }

        IconButton(onClick = onPreviousClick) {
            Icon(
                painter = painterResource(id = R.drawable.ic_skip_previous),
                contentDescription = "Previous",
                tint = TextPrimary,
                modifier = Modifier.size(32.dp)
            )
        }

        Surface(
            modifier = Modifier
                .size(72.dp)
                .clickable { onPlayPauseClick() },
            shape = CircleShape,
            color = PrimaryAccent
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    painter = if (isPlaying) 
                        painterResource(id = R.drawable.ic_pause)
                    else 
                        painterResource(id = R.drawable.ic_play),
                    contentDescription = if (isPlaying) "Pause" else "Play",
                    tint = BackgroundColor,
                    modifier = Modifier.size(36.dp)
                )
            }
        }

        IconButton(onClick = onNextClick) {
            Icon(
                painter = painterResource(id = R.drawable.ic_skip_next),
                contentDescription = "Next",
                tint = TextPrimary,
                modifier = Modifier.size(32.dp)
            )
        }

        IconButton(onClick = onRepeatClick) {
            Icon(
                painter = painterResource(id = R.drawable.ic_repeat),
                contentDescription = "Repeat",
                tint = when (repeatMode) {
                    RepeatMode.OFF -> TextSecondary
                    RepeatMode.ONE, RepeatMode.ALL -> PrimaryAccent
                },
                modifier = Modifier.size(24.dp)
            )
        }
    }
}

@Composable
private fun TechnicalInfoSection(
    format: String,
    bitrate: String,
    license: String,
    modifier: Modifier = Modifier
) {
    var isExpanded by remember { mutableStateOf(true) }

    Surface(
        modifier = modifier
            .fillMaxWidth()
            .animateContentSize(),
        shape = RoundedCornerShape(12.dp),
        color = SurfaceColor
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { isExpanded = !isExpanded },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.Info,
                        contentDescription = null,
                        tint = PrimaryAccent,
                        modifier = Modifier.size(20.dp)
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "TECHNICAL INFO",
                        style = MaterialTheme.typography.labelMedium,
                        color = TextPrimary,
                        letterSpacing = 1.sp
                    )
                }
                Icon(
                    imageVector = Icons.Default.KeyboardArrowDown,
                    contentDescription = if (isExpanded) "Collapse" else "Expand",
                    tint = TextSecondary,
                    modifier = Modifier.rotate(if (isExpanded) 180f else 0f)
                )
            }

            if (isExpanded) {
                Spacer(modifier = Modifier.height(16.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    TechnicalInfoItem(label = "FORMAT", value = format)
                    TechnicalInfoItem(label = "BITRATE", value = bitrate)
                    TechnicalInfoItem(label = "LICENSE", value = license)
                }
            }
        }
    }
}

@Composable
private fun TechnicalInfoItem(
    label: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = TextMuted
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            color = TextPrimary,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
private fun SimilarTracksSection(
    tracks: List<Track>,
    onTrackClick: (Track) -> Unit,
    onViewQueueClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "SIMILAR TRACKS",
                style = MaterialTheme.typography.labelMedium,
                color = TextPrimary,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )
            Text(
                text = "View Queue",
                style = MaterialTheme.typography.bodyMedium,
                color = PrimaryAccent,
                modifier = Modifier.clickable { onViewQueueClick() }
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyRow(
            contentPadding = PaddingValues(horizontal = 24.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(tracks) { track ->
                SimilarTrackItem(
                    track = track,
                    onClick = { onTrackClick(track) }
                )
            }
        }
    }
}

@Composable
private fun SimilarTrackItem(
    track: Track,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .width(120.dp)
            .clickable { onClick() }
    ) {
        AsyncImage(
            model = Dummy.dummyAlbum.image,
            contentDescription = track.name,
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(1f)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = track.name,
            style = MaterialTheme.typography.bodyMedium,
            color = TextPrimary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            text = Dummy.dummyAlbum.artistName,
            style = MaterialTheme.typography.bodySmall,
            color = TextSecondary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
private fun BottomActionBar(
    onDevicesClick: () -> Unit,
    onShareClick: () -> Unit,
    onLikedClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        BottomActionItemWithPainter(
            iconRes = R.drawable.ic_devices,
            label = "DEVICES",
            onClick = onDevicesClick
        )

        BottomActionItem(
            icon = Icons.Default.Share,
            label = "SHARE",
            onClick = onShareClick
        )

        BottomActionItem(
            icon = Icons.Default.Favorite,
            label = "LIKED",
            onClick = onLikedClick,
            tint = SecondaryAccent
        )
    }
}

@Composable
private fun BottomActionItem(
    icon: ImageVector,
    label: String,
    onClick: () -> Unit,
    tint: Color = TextSecondary,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = tint,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = tint
        )
    }
}

@Composable
private fun BottomActionItemWithPainter(
    iconRes: Int,
    label: String,
    onClick: () -> Unit,
    tint: Color = TextSecondary,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = iconRes),
            contentDescription = label,
            tint = tint,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = label,
            style = MaterialTheme.typography.labelSmall,
            color = tint
        )
    }
}

private fun formatDuration(millis: Long): String {
    val totalSeconds = millis / 1000
    val minutes = totalSeconds / 60
    val seconds = totalSeconds % 60
    return String.format("%02d:%02d", minutes, seconds)
}

private fun getLicenseShortName(licenseUrl: String): String {
    return when {
        licenseUrl.contains("by-nc-sa") -> "CC BY-NC-SA"
        licenseUrl.contains("by-nc-nd") -> "CC BY-NC-ND"
        licenseUrl.contains("by-nc") -> "CC BY-NC"
        licenseUrl.contains("by-sa") -> "CC BY-SA"
        licenseUrl.contains("by-nd") -> "CC BY-ND"
        licenseUrl.contains("by") -> "CC BY"
        else -> "CC"
    }
}

@Preview(showSystemUi = true, backgroundColor = 0xFF0D0D14)
@Composable
private fun PlayTrackScreenPreview() {
    PlayTrackScreen(
        musicState = MusicState(
            currentTrack = Dummy.dummyTracks.first().copy(name = "Shadow Runner"),
            playlist = Dummy.dummyTracks,
            progress = 165000L,
            duration = 260000L,
            isPlaying = true,
            isShuffleEnabled = true,
            repeatMode = RepeatMode.OFF
        )
    )
}
