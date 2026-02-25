package com.walcker.flickly.products.audio.features.ui.home

import cafe.adriel.voyager.core.model.screenModelScope
import com.walcker.flickly.core.navigation.NavigatorHolder
import com.walcker.flickly.core.ui.stepModel.StepModel
import com.walcker.flickly.products.audio.features.domain.manager.AudioManager
import com.walcker.flickly.products.audio.features.domain.model.AudioBook
import com.walcker.flickly.products.audio.features.domain.model.BookTab
import com.walcker.flickly.products.audio.features.domain.model.Language
import com.walcker.flickly.products.audio.features.ui.chapter.ChapterStep
import com.walcker.flickly.products.audio.strings.AudioStringsHolder
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
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
        val language = state.value.selectedLanguage
        val booksResult = audioManager.getAvailableBooks(language)
        val languages = audioManager.getLanguage()
        booksResult.onSuccess { books ->
            val initialBook = books.firstOrNull() ?: state.value.selectedBook
            val (oldTestament, newTestament) = splitTestaments(books)
            mutableState.update {
                it.copy(
                    loading = false,
                    strings = stringsHolder.strings.homeAudioStrings,
                    bibleBooksStrings = stringsHolder.strings.bibleBooksStrings,
                    languages = languages.getOrElse { persistentListOf() }.toImmutableList(),
                    availableBooks = books.toImmutableList(),
                    availableOldTestamentBooks = oldTestament,
                    availableNewTestamentBooks = newTestament,
                    selectedBook = initialBook,
                    tabs = buildTabs(hasOT = oldTestament.isNotEmpty(), hasNT = newTestament.isNotEmpty()).toImmutableList(),
                )
            }
        }.onFailure { e ->
            mutableState.update { it.copy(loading = false) }
            eventChannel.trySend(HomeAudioInternalEvents.OnError(e.message ?: "Erro ao carregar livros"))
        }
    }

    override fun onEvent(event: HomeAudioInternalRoute) {
        when (event) {
            HomeAudioInternalRoute.OnRetry -> screenModelScope.launch { loadInitial() }
            HomeAudioInternalRoute.OnPopBackStack -> navigatorHolder.navigator.pop()
            is HomeAudioInternalRoute.OnSelectBook -> onSelectBook(event.audioBook)
            is HomeAudioInternalRoute.OnSelectLanguage -> onSelectLanguage(event.language)
            is HomeAudioInternalRoute.OnSelectTab -> mutableState.update { it.copy(selectedTab = event.tab) }
        }
    }

    private fun onSelectLanguage(language: Language) {
        mutableState.update { it.copy(selectedLanguage = language, loading = true) }
        screenModelScope.launch {
            val booksResult = audioManager.getAvailableBooks(language)
            booksResult.onSuccess { books ->
                val initialBook = books.firstOrNull() ?: state.value.selectedBook
                val (oldTestament, newTestament) = splitTestaments(books)
                mutableState.update {
                    it.copy(
                        loading = false,
                        availableBooks = books.toImmutableList(),
                        availableOldTestamentBooks = oldTestament,
                        availableNewTestamentBooks = newTestament,
                        selectedBook = initialBook,
                        selectedTab = BookTab.All,
                        tabs = buildTabs(hasOT = oldTestament.isNotEmpty(), hasNT = newTestament.isNotEmpty()).toImmutableList(),
                    )
                }
            }.onFailure { e ->
                mutableState.update { it.copy(loading = false) }
                eventChannel.trySend(HomeAudioInternalEvents.OnError(e.message ?: "Erro ao carregar livros"))
            }
        }
    }

    private fun onSelectBook(book: AudioBook) {
        val language = state.value.selectedLanguage
        val ntStartIndex = AudioBook.entries.indexOf(AudioBook.Matthew).takeIf { it >= 0 } ?: 0
        val testament = if (AudioBook.entries.indexOf(book) < ntStartIndex) BookTab.OldTestament else BookTab.NewTestament
        mutableState.update { it.copy(selectedBook = book) }
        navigatorHolder.navigator.push(ChapterStep(language, book, testament))
    }


    private fun buildTabs(hasOT: Boolean, hasNT: Boolean): List<BookTab> = buildList {
        add(BookTab.All)
        if (hasOT) add(BookTab.OldTestament)
        if (hasNT) add(BookTab.NewTestament)
    }

    private fun splitTestaments(books: List<AudioBook>): Pair<ImmutableList<AudioBook>, ImmutableList<AudioBook>> {
        if (books.isEmpty()) return persistentListOf<AudioBook>() to persistentListOf()

        val all = AudioBook.entries
        val ntStartIndex = all.indexOf(AudioBook.Matthew).takeIf { it >= 0 } ?: 0
        val uniqueBooks = books.distinct().sortedBy { all.indexOf(it) }.toList()

        val oldTestament = uniqueBooks.filter { book -> all.indexOf(book) in 0 until ntStartIndex }.toImmutableList()
        val newTestament = uniqueBooks.filter { book -> all.indexOf(book) >= ntStartIndex }.toImmutableList()

        return oldTestament to newTestament
    }
}