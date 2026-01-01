package com.mdunggggg.jamendo_music.route

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import com.mdunggggg.jamendo_music.screen.home.HomeScreen

@Composable
fun JamendoApplicationRoot(modifier: Modifier = Modifier) {
    val navigationState = rememberJamendoNavigationState(
        startRoot = JamendoRoute.Home,
        topLevelRoutes = JAMENDO_TOP_LEVEL_DESTINATIONS.keys.toList()
    )
    val navigator = JamendoNavigator(state = navigationState)
    Scaffold(
        bottomBar = {
            JamendoAppBar(
                modifier = modifier,
                selectedRoute = navigationState.topLevelRoot,
                onSelectRoute = { navKey -> navigator.navigate(navKey) }
            )
        }
    ) { innerPadding ->
        NavDisplay(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            entries = navigationState.toEntries(
                entryProvider {
                    entry<JamendoRoute.Home> {
                        HomeScreen()
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
                }
            ),
            onBack = navigator::goBack,
        )
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