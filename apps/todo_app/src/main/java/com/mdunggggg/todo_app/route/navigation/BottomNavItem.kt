package com.mdunggggg.todo_app.route.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.ui.graphics.vector.ImageVector
import com.mdunggggg.todo_app.route.TodoRoute

data class BottomNavItem(
    val icon : ImageVector,
    val title : String,
)

val TOP_LEVELS_DESTINATION = mapOf(
    TodoRoute.TodoList to BottomNavItem(
        icon = Icons.Default.List,
        title = "List",
    ),
    TodoRoute.TodoFavorites to BottomNavItem(
        icon = Icons.Default.FavoriteBorder,
        title = "Favorites",
    ),
    TodoRoute.Settings to BottomNavItem(
        icon = Icons.Default.Settings,
        title = "Settings",
    )
)