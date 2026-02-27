package com.walcker.flickly.products.audio.features.ui.home

internal interface HomeAudioInternalEvents {
    data class OnError(val errorMessage: String) : HomeAudioInternalEvents
    data object OnShowChangePassword : HomeAudioInternalEvents
    data object OnSamePassword : HomeAudioInternalEvents
    data object OnPasswordChangedSuccess : HomeAudioInternalEvents
}