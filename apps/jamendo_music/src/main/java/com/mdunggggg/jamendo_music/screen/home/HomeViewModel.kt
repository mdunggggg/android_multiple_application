package com.mdunggggg.jamendo_music.screen.home

import com.mdunggggg.core_ui.BaseViewModel
import com.mindy.jamendo_core_data.model.Radio
import com.mindy.jamendo_core_data.repository.JamendoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import javax.inject.Inject

data class HomeDataState(
    val radios : List<Radio> = emptyList()
)

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val jamendoRepository: JamendoRepository
) : BaseViewModel<HomeDataState>(HomeDataState()){

    init {
        fetchData()
    }

    fun fetchData() {
        safelyLaunch {
            val radios = jamendoRepository.fetchRadios().getOrNull()
                ?: emptyList()
            currentData.copy(radios = radios)
        }
    }

}