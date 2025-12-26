package com.mdunggggg.todo_app.screens

import com.mdunggggg.core_ui.BaseViewModel

data class TodoListData(
    val todos : List<String> = (1..100).map { "Todo $it" }
)

class TodoListViewModel() : BaseViewModel<TodoListData>(TodoListData())