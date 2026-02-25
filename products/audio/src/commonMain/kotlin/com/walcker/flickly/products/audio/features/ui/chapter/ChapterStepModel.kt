package com.walcker.flickly.products.audio.features.ui.chapter

import cafe.adriel.voyager.core.model.screenModelScope
import com.walcker.flickly.core.navigation.NavigatorHolder
import com.walcker.flickly.core.ui.stepModel.StepModel
import com.walcker.flickly.products.audio.features.domain.manager.AudioManager
import com.walcker.flickly.products.audio.features.domain.model.AudioBook
import com.walcker.flickly.products.audio.features.domain.model.BookTab
import com.walcker.flickly.products.audio.features.domain.model.Language
import com.walcker.flickly.products.audio.strings.AudioStringsHolder
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class ChapterStepModel internal constructor(
    private val audioManager: AudioManager,
    private val stringsHolder: AudioStringsHolder,
    private val navigatorHolder: NavigatorHolder,
) : StepModel<ChapterState, ChapterInternalRoute>(ChapterState()) {

    private val eventChannel = Channel<ChapterInternalEvents>()
    val events = eventChannel.receiveAsFlow()

    override fun onEvent(event: ChapterInternalRoute) {
        when (event) {
            ChapterInternalRoute.OnPopBackStack -> navigatorHolder.navigator.pop()
            ChapterInternalRoute.OnRetryAudio -> retryAudio()
            is ChapterInternalRoute.OnLoad -> onLoad(event.language, event.book, event.testament)
            is ChapterInternalRoute.OnSelectChapter -> onSelectChapter(event.chapter)
        }
    }

    private fun onLoad(language: Language, book: AudioBook, testament: BookTab) {
        mutableState.update {
            it.copy(
                loading = true,
                selectedBook = book,
                selectedLanguage = language,
                testament = testament,
                strings = stringsHolder.strings.chapterStrings,
                bibleBooksStrings = stringsHolder.strings.bibleBooksStrings,
            )
        }
        fetchAudio(language, book, 1)
        screenModelScope.launch {
            audioManager.getAvailableChapters(language, book)
                .onSuccess { chapters ->
                    val firstChapter = chapters.firstOrNull() ?: 1
                    mutableState.update {
                        it.copy(
                            loading = false,
                            availableChapters = chapters.toImmutableList(),
                            selectedChapter = firstChapter,
                        )
                    }
                }
                .onFailure { e ->
                    mutableState.update { it.copy(loading = false) }
                    eventChannel.trySend(ChapterInternalEvents.OnError(e.message ?: "Erro ao carregar capítulos"))
                }
        }
    }

    private fun onSelectChapter(chapter: Int) {
        val current = state.value
        if (chapter !in current.availableChapters) return
        mutableState.update { it.copy(selectedChapter = chapter, audioUrl = null) }
        fetchAudio(current.selectedLanguage, current.selectedBook, chapter)
    }

    private fun retryAudio() {
        val current = state.value
        mutableState.update { it.copy(audioUrl = null) }
        fetchAudio(current.selectedLanguage, current.selectedBook, current.selectedChapter)
    }

    private fun fetchAudio(language: Language, book: AudioBook, chapter: Int) {
        screenModelScope.launch {
            audioManager.getAudioUrl(language = language, book = book, chapter = chapter)
                .onSuccess { audioUrl ->
                    mutableState.update { it.copy(audioUrl = audioUrl) }
                }
                .onFailure { e ->
                    eventChannel.trySend(ChapterInternalEvents.OnError(e.message ?: "Erro ao carregar áudio"))
                }
        }
    }
}
