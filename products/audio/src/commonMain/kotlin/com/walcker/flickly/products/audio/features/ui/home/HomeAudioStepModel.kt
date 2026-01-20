package com.walcker.flickly.products.audio.features.ui.home

import cafe.adriel.voyager.core.model.screenModelScope
import com.walcker.flickly.core.navigation.NavigatorHolder
import com.walcker.flickly.core.ui.stepModel.StepModel
import com.walcker.flickly.products.audio.features.domain.manager.AudioManager
import com.walcker.flickly.products.audio.features.domain.model.AudioBook
import com.walcker.flickly.products.audio.features.domain.model.Language
import com.walcker.flickly.products.audio.strings.AudioStringsHolder
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class HomeAudioStepModel internal constructor(
    private val audioManager: AudioManager,
    private val stringsHolder: AudioStringsHolder,
    private val navigatorHolder: NavigatorHolder,
) : StepModel<HomeAudioState, HomeAudioInternalRoute>(HomeAudioState(loading = true)) {

    private val eventChannel = Channel<HomeAudioInternalEvents>()
    val events = eventChannel.receiveAsFlow()

    init {
        screenModelScope.launch { loadInitial() }
    }

    private suspend fun loadInitial() {
        val booksResult = audioManager.getAvailableBooks(Language.URDU)
        booksResult.onSuccess { books ->
            val initialBook = books.firstOrNull() ?: state.value.selectedBook
            mutableState.update {
                it.copy(
                    strings = stringsHolder.strings.audioHomeStrings,
                    bibleBooksStrings = stringsHolder.strings.bibleBooksStrings,
                    availableBooks = books.toImmutableList(),
                    selectedBook = initialBook
                )
            }
            loadChapters(initialBook) { chapters ->
                val initialChapter = chapters.firstOrNull() ?: 1
                mutableState.update { s -> s.copy(selectedChapter = initialChapter) }
                fetchAudio(initialBook, initialChapter)
            }
        }.onFailure { e ->
            mutableState.update { it.copy(loading = false) }
            eventChannel.trySend(HomeAudioInternalEvents.OnError(e.message ?: "Erro ao carregar livros"))
        }
    }

    override fun onEvent(event: HomeAudioInternalRoute) {
        when (event) {
            HomeAudioInternalRoute.OnRetry -> fetchAudio(state.value.selectedBook, state.value.selectedChapter, force = true)
            HomeAudioInternalRoute.OnPopBackStack -> navigatorHolder.navigator.pop()
            is HomeAudioInternalRoute.OnSelectBook -> onSelectBook(event.audioBook)
            is HomeAudioInternalRoute.OnSelectChapter -> onSelectChapter(event.chapter)
        }
    }

    private fun onSelectBook(book: AudioBook) {
        mutableState.update { it.copy(selectedBook = book, selectedChapter = 1, audioUrl = null) }
        screenModelScope.launch {
            loadChapters(book) { chapters ->
                val firstChapter = chapters.firstOrNull() ?: 1
                mutableState.update { s -> s.copy(selectedChapter = firstChapter) }
                fetchAudio(book, firstChapter, force = true)
            }
        }
    }

    private fun onSelectChapter(chapter: Int) {
        val current = state.value
        if (chapter !in current.availableChapters) return
        mutableState.update { it.copy(selectedChapter = chapter, audioUrl = null) }
        fetchAudio(current.selectedBook, chapter)
    }

    private fun loadChapters(book: AudioBook, onLoaded: (List<Int>) -> Unit) {
        screenModelScope.launch {
            audioManager.getAvailableChapters(Language.URDU, book)
                .onSuccess { chapters ->
                    mutableState.update { it.copy(availableChapters = chapters.toImmutableList()) }
                    onLoaded(chapters)
                }
                .onFailure { e ->
                    mutableState.update { it.copy(loading = false) }
                    eventChannel.trySend(HomeAudioInternalEvents.OnError(e.message ?: "Erro ao carregar capítulos"))
                }
        }
    }

    private fun fetchAudio(book: AudioBook, chapter: Int, force: Boolean = false) {
        val current = state.value
        if (!force && current.audioUrl != null && current.selectedBook == book && current.selectedChapter == chapter) return
        screenModelScope.launch {
            audioManager.getAudioUrl(
                language = Language.URDU,
                book = book,
                chapter = chapter,
            )
                .onSuccess { audioUrl ->
                    mutableState.update { s -> s.copy(audioUrl = audioUrl, loading = false) }
                }
                .onFailure { error ->
                    mutableState.update { s -> s.copy(loading = false) }
                    eventChannel.trySend(HomeAudioInternalEvents.OnError(error.message ?: "Unknown Error"))
                }
        }
    }
}