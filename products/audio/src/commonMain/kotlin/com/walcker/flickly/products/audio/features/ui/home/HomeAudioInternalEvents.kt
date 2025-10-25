package com.walcker.flickly.products.audio.features.ui.home

internal interface HomeAudioInternalEvents {
    data class OnError(val errorMessage: String) : HomeAudioInternalEvents
}