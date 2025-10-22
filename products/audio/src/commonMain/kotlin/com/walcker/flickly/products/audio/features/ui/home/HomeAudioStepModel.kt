package com.walcker.flickly.products.audio.features.ui.home

import cafe.adriel.voyager.core.model.screenModelScope
import com.walcker.flickly.core.ui.stepModel.StepModel
import com.walcker.flickly.products.audio.features.domain.manager.AudioManager
import com.walcker.movies.core.navigation.NavigatorHolder
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class HomeAudioStepModel internal constructor(
    private val audioManager: AudioManager,
    private val navigatorHolder: NavigatorHolder,
) : StepModel<HomeAudioState, HomeAudioInternalRoute>(HomeAudioState()) {

    init {
        getAudio()
    }

    override fun onEvent(event: HomeAudioInternalRoute) {
        when (event) {
            HomeAudioInternalRoute.OnRetry -> getOnRetry()
            HomeAudioInternalRoute.OnPopBackStack -> navigatorHolder.navigator.pop()
        }
    }

    private fun getOnRetry() {
        mutableState.update { currentState ->
            currentState.copy(
                errorMessage = null,
                loading = true
            )
        }
        getAudio()
    }

    private fun getAudio() {
        screenModelScope.launch {
            audioManager.getAudioUrl()
                .onSuccess { audioUrl ->
                    mutableState.update { currentState ->
                        currentState.copy(
                            audioUrl = audioUrl,
                            loading = false,
                        )
                    }
                }
                .onFailure { error ->
                    mutableState.update { currentState ->
                        currentState.copy(
                            errorMessage = error.message,
                            loading = false,
                        )
                    }
                }
        }
    }
}