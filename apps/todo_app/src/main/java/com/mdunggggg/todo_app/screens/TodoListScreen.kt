package com.mdunggggg.todo_app.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mdunggggg.core_ui.BaseScreen

@Composable
fun TodoListScreen(
    viewModel: TodoListViewModel = viewModel(),
    onTodoClick: (String) -> Unit = { }
) {
    BaseScreen(
        viewModel = viewModel
    ) { data ->
        LazyColumn {
            data.todos.forEach { todo ->
                item {
                    Text(
                        text = todo,
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                onTodoClick(todo)
                            }
                            .padding(16.dp),
                        textAlign = TextAlign.Center
                    )
                }
            }
        }
    }
}