package com.mdunggggg.core_ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mdunggggg.core_util.network.NetworkManager
import com.mdunggggg.core_util.network.NetworkObserver
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

abstract class BaseViewModel<DATA>(initData: DATA) : ViewModel(), NetworkObserver {

    private val _uiState = MutableStateFlow(UiState(data = initData))
    val uiState = _uiState.asStateFlow()

    protected val currentData: DATA
        get() = _uiState.value.data

    private val _connectivity = MutableStateFlow(true)
    val connectivity = _connectivity.asStateFlow()

    init {
        NetworkManager.addObserver(this)
    }

    override fun onConnectivityChange(isOnline: Boolean) {
        _connectivity.value = isOnline
    }

    protected fun safelyLaunch(
        showLoading: Boolean = true,
        block: suspend CoroutineScope.() -> DATA
    ) {
        viewModelScope.launch {
            try {
                if (showLoading) {
                    _uiState.update { it.copy(isLoading = true, error = null) }
                }
                val newData = block()
                _uiState.update {
                    it.copy(data = newData, isLoading = false)
                }

            } catch (e: Exception) {
                e.printStackTrace()
                _uiState.update {
                    it.copy(error = e, isLoading = false)
                }
            }
        }
    }

    protected fun updateData(reducer: (DATA) -> DATA) {
        _uiState.update { it.copy(data = reducer(it.data)) }
    }

    open fun clearError() {
        _uiState.update {
            it.copy(error = null)
        }
    }

    override fun onCleared() {
        super.onCleared()
        NetworkManager.removeObserver(this)
    }
}