package com.mdunggggg.jamendo_music.route

import android.util.Log
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
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.rememberDecoratedNavEntries
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.savedstate.compose.serialization.serializers.MutableStateSerializer
import androidx.savedstate.serialization.SavedStateConfiguration
import kotlinx.serialization.PolymorphicSerializer
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

class JamendoNavigationState(
    val startRoot: NavKey,
    topLevelRoot: MutableState<NavKey>,
    val backStacks: Map<NavKey, NavBackStack<NavKey>>
) {
    var topLevelRoot by topLevelRoot

    val stackInUse : List<NavKey>
        get() = if (startRoot == topLevelRoot) {
            listOf(startRoot)
        } else {
            listOf(startRoot, topLevelRoot)
        }
}

@Composable
fun rememberJamendoNavigationState(
    startRoot: NavKey,
    topLevelRoutes: List<NavKey>
): JamendoNavigationState {
    val topLevelRoot = rememberSerializable(
        startRoot,
        topLevelRoutes,
        configuration = configuration,
        serializer = MutableStateSerializer(PolymorphicSerializer(NavKey::class))
    ) {
        mutableStateOf(startRoot)
    }

    val backStacks = topLevelRoutes.associateWith { route ->
        rememberNavBackStack(configuration = configuration, route)
    }

    return remember(startRoot, topLevelRoutes) {
        JamendoNavigationState(
            startRoot = startRoot,
            topLevelRoot = topLevelRoot,
            backStacks = backStacks
        )
    }
}

@Composable
fun JamendoNavigationState.toEntries(
    entryBuilder: (NavKey) -> NavEntry<NavKey>
): SnapshotStateList<NavEntry<NavKey>> {
    val decoratorEntries = backStacks.mapValues { (_, backStack) ->
        val decorators = listOf(
            rememberSaveableStateHolderNavEntryDecorator<NavKey>(),
            rememberViewModelStoreNavEntryDecorator()
        )
        rememberDecoratedNavEntries(
            backStack = backStack,
            entryDecorators = decorators,
            entryProvider = entryBuilder
        )
    }
    return stackInUse.flatMap {
        decoratorEntries[it] ?: emptyList()
    }.toMutableStateList()
}

val configuration = SavedStateConfiguration {
    serializersModule = SerializersModule {
        polymorphic(NavKey::class) {
            subclass(
                JamendoRoute.Home::class,
                JamendoRoute.Home.serializer()
            )
            subclass(
                JamendoRoute.Search::class,
                JamendoRoute.Search.serializer()
            )
            subclass(
                JamendoRoute.Library::class,
                JamendoRoute.Library.serializer()
            )
            subclass(
                JamendoRoute.Profile::class,
                JamendoRoute.Profile.serializer()
            )
            subclass(
                JamendoRoute.DetailAlbum::class,
                JamendoRoute.DetailAlbum.serializer()
            )
            subclass(
                JamendoRoute.PlayTrack::class,
                JamendoRoute.PlayTrack.serializer()
            )
        }
    }
}