package com.mdunggggg.jamendo_music.screen.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mindy.jamendo_core_data.repository.JamendoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val jamendoRepository: JamendoRepository
) : ViewModel(){
    fun fetchRadios() {
        viewModelScope.launch {
            val result = jamendoRepository.fetchRadios(limit = 20, offset = 0, order = "id_desc")
        }
    }
}