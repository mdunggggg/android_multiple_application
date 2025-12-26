package com.mdunggggg.todo_app.route

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
sealed interface TodoRoute : NavKey {

    @Serializable
    data object TodoList : TodoRoute

    @Serializable
    data object TodoFavorites : TodoRoute

    @Serializable
    data class TodoDetail(val todo : String) : TodoRoute

    @Serializable
    data object Settings : TodoRoute
}