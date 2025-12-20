package com.mdunggggg.core_ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mdunggggg.core_util.NetworkObserver
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<DATA>(initData: DATA) : ViewModel(), NetworkObserver {

    private val _uiState = MutableStateFlow(UiState(data = initData))
    val uiState = _uiState.asStateFlow()

    protected val currentData: DATA
        get() = _uiState.value.data

    private val connectivityChannel = Channel<Boolean>(Channel.UNLIMITED)
    val connectivity = connectivityChannel.receiveAsFlow()

    override fun onConnectivityChange(isOnline: Boolean) {
        connectivityChannel.trySend(isOnline)
    }

    protected open val viewModelCoroutineExceptionHandler =
        CoroutineExceptionHandler { _, throwable ->
            viewModelScope.launch {
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        error = throwable
                    )
                }
            }
        }

    fun safelyLaunch(block: suspend CoroutineScope.() -> Unit) =
        viewModelScope.launch(viewModelCoroutineExceptionHandler) {
            block()
        }

    open fun clearError() {
        _uiState.update {
            it.copy(error = null)
        }
    }

}