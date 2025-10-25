package com.walcker.flickly.products.audio.features.domain.manager

import com.walcker.flickly.products.audio.features.domain.model.AudioBook
import com.walcker.flickly.products.audio.features.domain.model.Language

internal interface AudioManager {

    suspend fun getAudioUrl(
        language: Language,
        book: AudioBook,
        chapter: Int,
    ): Result<String>

    suspend fun getAvailableBooks(language: Language): Result<List<AudioBook>>

    suspend fun getAvailableChapters(language: Language, book: AudioBook): Result<List<Int>>
}