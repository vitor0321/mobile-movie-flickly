package com.walcker.flickly.products.audio.features.ui.home

internal data class HomeAudioState(
    val loading: Boolean = true,
    val audioUrl: String? = null,
    val errorMessage: String? = null,
)