package com.mdunggggg.todo_app.route.navigation

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavKey

@Composable
fun TodoNav(
    modifier: Modifier = Modifier,
    selectedKey : NavKey,
    onSelectKey : (NavKey) -> Unit
) {
    BottomAppBar(
        modifier = modifier,
    ) {
        TOP_LEVELS_DESTINATION.forEach { (topLevelDestination, bottomNavItem) ->
            NavigationBarItem(
                selected = topLevelDestination == selectedKey,
                icon = {
                    Icon(
                        imageVector = bottomNavItem.icon,
                        contentDescription = bottomNavItem.title
                    )
                },
                label = {
                    Text(text = bottomNavItem.title)
                },
                onClick = {
                    onSelectKey(topLevelDestination)
                }
            )
        }
    }
}