package com.walcker.flickly.products.audio.features.ui.home

internal interface HomeAudioInternalRoute {
    data object OnRetry : HomeAudioInternalRoute
    data object OnPopBackStack : HomeAudioInternalRoute
}