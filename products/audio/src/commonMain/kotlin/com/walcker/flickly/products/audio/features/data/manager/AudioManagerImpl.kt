package com.walcker.flickly.products.audio.features.data.manager

import androidx.compose.ui.text.toLowerCase
import com.walcker.flickly.core.data.firebase.FirebaseStorageService
import com.walcker.flickly.core.data.handle.withRetry
import com.walcker.flickly.products.audio.features.domain.manager.AudioManager
import com.walcker.flickly.products.audio.features.domain.model.AudioBook
import com.walcker.flickly.products.audio.features.domain.model.Language
import kotlinx.coroutines.CoroutineDispatcher

internal class AudioManagerImpl(
    private val storageService: FirebaseStorageService,
    private val dispatcher: CoroutineDispatcher,
) : AudioManager {

    override suspend fun getAudioUrl(
        language: Language,
        book: AudioBook,
        chapter: Int,
    ): Result<String> = withRetry(dispatcher = dispatcher) {
        if (chapter <= 0 || chapter > book.totalChapters) {
            return@withRetry Result.failure(IllegalArgumentException("Capítulo inválido para ${book.name}: $chapter"))
        }
        val audioPath = buildPath(language, book, chapter)
        storageService.getDownloadUrl(audioPath)
    }

    override suspend fun getAvailableBooks(language: Language): Result<List<AudioBook>> =
        withRetry(dispatcher) {
            val basePath = "audio/${language.folderName}"
            val foldersResult = storageService.listFolders(basePath)
            foldersResult.map { folderNames ->
                folderNames.mapNotNull { folder ->
                    AudioBook.entries.firstOrNull { it.name.lowercase() == folder }
                }.sortedBy { it.ordinal }
            }
        }

    override suspend fun getAvailableChapters(language: Language, book: AudioBook): Result<List<Int>> =
        withRetry(dispatcher) {
            val path = "audio/${language.folderName}/${book.name.lowercase()}"
            val filesResult = storageService.listFiles(path)
            filesResult.map { fileNames ->
                fileNames.mapNotNull { name -> name.substringBefore('.').toIntOrNull() }.sorted()
            }
        }

    private fun buildPath(language: Language, book: AudioBook, chapter: Int): String =
        "audio/${language.folderName}/${book.name.lowercase()}/$chapter.mp3"
}