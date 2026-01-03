package com.mdunggggg.jamendo_music.route

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector

data class JamendoBottomNavItem(
    val icon : ImageVector,
    val title : String,
)

val JAMENDO_TOP_LEVEL_DESTINATIONS = mapOf(
    JamendoRoute.Home to JamendoBottomNavItem(
        icon = Icons.Default.Home,
        title = "Home",
    ),

    JamendoRoute.Search to JamendoBottomNavItem(
        icon = Icons.Default.Search,
        title = "Search",
    ),

    JamendoRoute.Library to JamendoBottomNavItem(
        icon = Icons.Default.Menu,
        title = "Library",
    ),

    JamendoRoute.Profile to JamendoBottomNavItem(
        icon = Icons.Default.Person,
        title = "Profile",
    )
)