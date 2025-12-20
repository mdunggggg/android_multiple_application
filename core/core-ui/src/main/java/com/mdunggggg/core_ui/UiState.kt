package com.mdunggggg.core_ui

data class UiState<T>(
    val data: T,
    val isLoading: Boolean = false,
    val error: Throwable? = null
)