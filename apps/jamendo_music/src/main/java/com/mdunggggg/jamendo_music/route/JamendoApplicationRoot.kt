package com.mdunggggg.jamendo_music.route

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.mdunggggg.jamendo_music.di.MusicControllerEntryPoint
import com.mdunggggg.jamendo_music.player.controller.MusicControllerManagement
import com.mdunggggg.jamendo_music.player.ui.MiniPlayer
import com.mdunggggg.jamendo_music.screen.detail_album.DetailAlbumScreen
import com.mdunggggg.jamendo_music.screen.home.HomeScreen
import com.mdunggggg.jamendo_music.screen.play_track.PlayTrackScreen
import dagger.hilt.android.EntryPointAccessors

@Composable
fun JamendoApplicationRoot(
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current

    val musicControllerManager = remember {
        EntryPointAccessors.fromApplication(
            context.applicationContext,
            MusicControllerEntryPoint::class.java
        ).musicControllerManagement()
    }
    val navigationState = rememberJamendoNavigationState(
        startRoot = JamendoRoute.Home,
        topLevelRoutes = JAMENDO_TOP_LEVEL_DESTINATIONS.keys.toList()
    )
    val navigator = JamendoNavigator(state = navigationState)
    val isTopLevel = JAMENDO_TOP_LEVEL_DESTINATIONS.keys.contains(navigator.getCurrentRoute())

    val musicState by musicControllerManager.musicState.collectAsStateWithLifecycle()
    val showMiniPlayer = musicState.currentTrack != null && !navigator.isPlayTrackRoute()

    Scaffold(
        bottomBar = {
            if (isTopLevel) {
                JamendoAppBar(
                    selectedRoute = navigationState.topLevelRoot,
                    onSelectRoute = { navigator.navigate(it) }
                )
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            NavDisplay(
                modifier = Modifier
                    .fillMaxSize(),
                entries = navigationState.toEntries(
                    entryProvider {
                        entry<JamendoRoute.Home> {
                            HomeScreen(
                                onDetailAlbumClick = { id ->
                                    navigator.navigate(JamendoRoute.DetailAlbum(id = id))
                                }
                            )
                        }
                        entry<JamendoRoute.Search> {
                            Box {
                                Text("Search Screen")
                            }
                        }
                        entry<JamendoRoute.Library> {
                            Box {
                                Text("Library Screen")
                            }
                        }
                        entry<JamendoRoute.Profile> {
                            Box {
                                Text("Profile Screen")
                            }
                        }
                        entry<JamendoRoute.DetailAlbum> {
                            DetailAlbumScreen(
                                idAlbum = it.id,
                                onTrackPlay = { tracks, index ->
                                    musicControllerManager.setPlaylist(
                                        tracks,
                                        startIndex = index
                                    )
                                })
                        }
                        entry<JamendoRoute.PlayTrack> {
                            PlayTrackScreen(
                                musicState = musicState,
                                onBackClick = {
                                    navigator.goBack()
                                },
                                onMoreClick = {

                                },
                                onShuffleClick = {

                                },
                                onPreviousClick = {
                                    musicControllerManager.previous()
                                },
                                onPlayPauseClick = {
                                    if (musicState.isPlaying) {
                                        musicControllerManager.pause()
                                    } else {
                                        musicControllerManager.resume()
                                    }
                                },
                                onNextClick = {
                                    musicControllerManager.next()
                                },
                                onRepeatClick = {
                                },
                                onSeek = {

                                },
                                onDevicesClick = {},
                                onShareClick = {},
                                onLikedClick = {},
                                onSimilarTrackClick = {},
                                onViewQueueClick = {}
                            )
                        }
                    }
                ),
                onBack = navigator::goBack,
            )
            AnimatedVisibility(
                visible = showMiniPlayer,
                enter = slideInVertically(initialOffsetY = { it }) + fadeIn(),
                exit = slideOutVertically(targetOffsetY = { it }) + fadeOut(),
                modifier = Modifier
                    .align(Alignment.BottomCenter)
            ) {
                MiniPlayer(
                    musicState = musicState,
                    onPlayPauseClick = {
                        if (musicState.isPlaying) {
                            musicControllerManager.pause()
                        } else {
                            musicControllerManager.resume()
                        }
                    },
                    onMiniPlayerClick = {
                        navigator.navigate(JamendoRoute.PlayTrack(musicState = musicState))
                    }
                )
            }
        }
    }
}


@Composable
fun JamendoAppBar(
    modifier: Modifier = Modifier,
    selectedRoute: NavKey,
    onSelectRoute: (NavKey) -> Unit,
) {
    val defaultColor = Color(0xFF020408)
    BottomAppBar(
        modifier = modifier,
        containerColor = defaultColor,
    ) {
        JAMENDO_TOP_LEVEL_DESTINATIONS.forEach { (topLevelDestination, bottomNavItem) ->
            NavigationBarItem(
                selected = topLevelDestination == selectedRoute,
                onClick = { onSelectRoute(topLevelDestination) },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = defaultColor
                ),
                icon = {
                    Icon(
                        imageVector = bottomNavItem.icon,
                        contentDescription = bottomNavItem.title,
                        tint = if (topLevelDestination == selectedRoute) Color(0xFF2DD4BF)
                        else Color(0xFF64748B)
                    )
                },
                label = {
                    Text(
                        text = bottomNavItem.title,
                        color = if (topLevelDestination == selectedRoute) Color(0xFF2DD4BF)
                        else Color(0xFF64748B)
                    )
                },
            )
        }
    }
}