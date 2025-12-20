package com.mdunggggg.core_ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun <T> BaseScreen(
    viewModel: BaseViewModel<T>,
    content: @Composable (data: T) -> Unit
) {
    val state = viewModel.uiState.collectAsStateWithLifecycle()
    val networkConnectivity by viewModel.connectivity.collectAsStateWithLifecycle(
        initialValue = true
    )
    val snackbarHostState = remember { SnackbarHostState() }
    LaunchedEffect(networkConnectivity) {
        if (!networkConnectivity) {
            snackbarHostState.showSnackbar("No Internet Connection")
        }
        else {
            snackbarHostState.currentSnackbarData?.dismiss()
        }
    }
    Scaffold(snackbarHost = { SnackbarHost(hostState = snackbarHostState) }) {
        Box(modifier = Modifier.fillMaxSize().padding(it)) {
            content(state.value.data)
            if (state.value.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center)
                )
            }
            state.value.error?.let { error ->
                AlertDialog(
                    onDismissRequest = {
                        viewModel.clearError()
                    },
                    title = {
                        Text(text = "Error")
                    },
                    text = {
                        Text(text = error.localizedMessage ?: "Unknown Error")
                    },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                viewModel.clearError()
                            }
                        ) {
                            Text("OK")
                        }
                    }
                )
            }
        }
    }
}