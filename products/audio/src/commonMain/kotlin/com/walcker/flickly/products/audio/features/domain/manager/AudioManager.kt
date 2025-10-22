package com.walcker.flickly.products.audio.features.domain.manager

internal interface AudioManager {
    suspend fun getAudioUrl(): Result<String>
}