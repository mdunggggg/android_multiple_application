package com.mdunggggg.todo_app.route.navigation

import androidx.navigation3.runtime.NavKey

class Navigator(val state : NavigationState) {
    fun navigate(route : NavKey) {
        if (route in state.backStacks.keys) {
            state.topLevelRoot = route
        }
        else {
            state.backStacks[state.topLevelRoot]?.add(route)
        }
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
}