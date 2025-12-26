package com.mdunggggg.todo_app.screens

import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import com.mdunggggg.core_ui.BaseScreen

@Composable
fun TodoDetailScreen(
    todoDetail : String,
    viewModel: TodoDetailViewModel = viewModel {
        TodoDetailViewModel(data = TodoDetailData(title = todoDetail))
    }
) {
    BaseScreen(
        viewModel = viewModel
    ) { todoDetailData ->
        Text(
            text = todoDetailData.title,
            modifier = Modifier.fillMaxSize(),
            textAlign = TextAlign.Center
        )
    }
}