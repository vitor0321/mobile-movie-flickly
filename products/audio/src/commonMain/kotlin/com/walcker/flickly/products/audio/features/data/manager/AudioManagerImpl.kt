package com.walcker.flickly.products.audio.features.data.manager

import com.walcker.flickly.core.data.firebase.FirebaseStorageService
import com.walcker.flickly.core.data.handle.withRetry
import com.walcker.flickly.products.audio.features.domain.manager.AudioManager
import kotlinx.coroutines.CoroutineDispatcher

internal class AudioManagerImpl(
    private val storageService: FirebaseStorageService,
    private val dispatcher: CoroutineDispatcher,
) : AudioManager {
    override suspend fun getAudioUrl(): Result<String> =
        withRetry(dispatcher = dispatcher) {
            val audioPath = "audio/urdu/matthew/1.mp3"
            storageService.getDownloadUrl(audioPath)
        }
}