package com.mdunggggg.todo_app.screens

import com.mdunggggg.core_ui.BaseViewModel

data class TodoDetailData(
    val title : String,
)

class TodoDetailViewModel(
    data : TodoDetailData
) : BaseViewModel<TodoDetailData>(data) {

}