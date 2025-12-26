package com.mdunggggg.todo_app.route.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSerializable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.runtime.NavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberDecoratedNavEntries
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.savedstate.compose.serialization.serializers.MutableStateSerializer
import androidx.savedstate.serialization.SavedStateConfiguration
import com.mdunggggg.todo_app.route.TodoRoute
import kotlinx.serialization.PolymorphicSerializer
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

class NavigationState(
    val startRoot : NavKey,
    topLevelRoot : MutableState<NavKey>,
    val backStacks : Map<NavKey, NavBackStack<NavKey>>
) {
    var topLevelRoot by topLevelRoot
    val stackInUse : List<NavKey>
        get() = if (topLevelRoot == startRoot) {
            listOf(startRoot)
        }
        else {
            listOf(startRoot, topLevelRoot)
        }
}

@Composable
fun rememberNavigationState(
    startRoot: NavKey,
    topLevelRoots: List<NavKey>, // Main destinations of the app (bottom nav items)
) : NavigationState{

    val topLevelRoot = rememberSerializable(
        startRoot, topLevelRoots,
        configuration = configuration,
        serializer = MutableStateSerializer(PolymorphicSerializer(NavKey::class))
    ) {
        mutableStateOf(startRoot)
    }

    val backStacks = topLevelRoots.associateWith { root ->
        rememberNavBackStack(
            configuration = configuration,
            root
        )
    }

    return remember(startRoot, topLevelRoots) {
        NavigationState(
            startRoot = startRoot,
            topLevelRoot = topLevelRoot,
            backStacks = backStacks
        )
    }
}

@Composable
fun NavigationState.toEntries(
    entryBuilder : (NavKey) -> NavEntry<NavKey>
) : SnapshotStateList<NavEntry<NavKey>> {
    val decoratorEntries = backStacks.mapValues { (_, stack) ->
        val decorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator<NavKey>(),
            rememberViewModelStoreNavEntryDecorator()
        )
        rememberDecoratedNavEntries(
            backStack = stack,
            entryDecorators = decorators,
            entryProvider = entryBuilder
        )
    }
    return stackInUse.flatMap { decoratorEntries[it] ?: emptyList() }
        .toMutableStateList()
}

val configuration = SavedStateConfiguration {
    serializersModule = SerializersModule {
        polymorphic(NavKey::class) {
            subclass(TodoRoute.TodoList::class, TodoRoute.TodoList.serializer())
            subclass(TodoRoute.TodoDetail::class, TodoRoute.TodoDetail.serializer())
            subclass(TodoRoute.TodoFavorites::class, TodoRoute.TodoFavorites.serializer())
            subclass(TodoRoute.Settings::class, TodoRoute.Settings.serializer())
        }
    }
}

