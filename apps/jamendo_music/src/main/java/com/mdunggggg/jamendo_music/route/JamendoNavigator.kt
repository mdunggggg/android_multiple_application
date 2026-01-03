package com.mdunggggg.jamendo_music.route

import android.util.Log
import androidx.navigation3.runtime.NavKey

class JamendoNavigator(val state : JamendoNavigationState) {
    fun navigate(navKey : NavKey) {
        if (navKey in state.backStacks.keys) {
            state.topLevelRoot = navKey
        }
        else {
            state.backStacks[state.topLevelRoot]?.add(navKey)
        }
    }

    fun getCurrentRoute() : NavKey? {
        val currentStack = state.backStacks[state.topLevelRoot] ?: return null
        return currentStack.lastOrNull()
    }

    fun goBack() {
        val currentStack = state.backStacks[state.topLevelRoot] ?: return
        val currentRoute = currentStack.last()

        if (currentRoute == state.topLevelRoot) {
            state.topLevelRoot = state.startRoot
        }
        else {
            currentStack.removeLastOrNull()
        }
    }

    fun isPlayTrackRoute() : Boolean {
        val currentRoute = getCurrentRoute() ?: return false
        return currentRoute is JamendoRoute.PlayTrack
    }
}