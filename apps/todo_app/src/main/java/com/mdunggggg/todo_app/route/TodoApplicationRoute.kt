package com.mdunggggg.todo_app.route

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.savedstate.serialization.SavedStateConfiguration
import com.mdunggggg.todo_app.route.navigation.Navigator
import com.mdunggggg.todo_app.route.navigation.TOP_LEVELS_DESTINATION
import com.mdunggggg.todo_app.route.navigation.TodoNav
import com.mdunggggg.todo_app.route.navigation.configuration
import com.mdunggggg.todo_app.route.navigation.rememberNavigationState
import com.mdunggggg.todo_app.route.navigation.toEntries
import com.mdunggggg.todo_app.screens.TodoDetailScreen
import com.mdunggggg.todo_app.screens.TodoFavouriteScreen
import com.mdunggggg.todo_app.screens.TodoListScreen
import com.mdunggggg.todo_app.screens.TodoSettingScreen

@Composable
fun TodoApplicationRoute(
    modifier: Modifier = Modifier,
) {

    val navigationState = rememberNavigationState(
        startRoot = TodoRoute.TodoList,
        topLevelRoots = TOP_LEVELS_DESTINATION.keys.toList()
    )
    val navigator = Navigator(navigationState)

    Scaffold(
        bottomBar = {
            TodoNav(
                modifier = modifier,
                selectedKey = navigationState.topLevelRoot,
                onSelectKey = { key ->
                    navigator.navigate(key)
                }

            )
        }
    ) { innerPadding ->
        NavDisplay(
            modifier = Modifier.fillMaxSize().padding(innerPadding),
            onBack = navigator::goBack,
            entries = navigationState.toEntries(
                entryProvider {
                    entry<TodoRoute.TodoList> {
                        TodoListScreen(
                            onTodoClick = {
                                navigator.navigate(TodoRoute.TodoDetail(it))
                            }
                        )
                    }
                    entry<TodoRoute.TodoDetail> {
                        TodoDetailScreen(it.todo)
                    }
                    entry<TodoRoute.TodoFavorites> {
                        TodoFavouriteScreen(

                        )
                    }
                    entry<TodoRoute.Settings> {
                        TodoSettingScreen(

                        )
                    }
                }
            ),
        )
    }

}