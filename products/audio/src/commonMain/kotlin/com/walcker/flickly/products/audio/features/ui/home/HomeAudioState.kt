package com.walcker.flickly.products.audio.features.ui.home

import com.walcker.flickly.products.audio.features.domain.model.AudioBook
import com.walcker.flickly.products.audio.features.domain.model.BookTab
import com.walcker.flickly.products.audio.features.domain.model.Language
import com.walcker.flickly.products.audio.strings.HomeAudioStrings
import com.walcker.flickly.products.audio.strings.BibleBooksStrings
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

internal data class HomeAudioState(
    val loading: Boolean = false,
    val strings: HomeAudioStrings = HomeAudioStrings(),
    val bibleBooksStrings: BibleBooksStrings = BibleBooksStrings(),
    val selectedBook: AudioBook = AudioBook.Matthew,
    val selectedLanguage: Language = Language.URDU,
    val selectedTab: BookTab = BookTab.All,
    val tabs: ImmutableList<BookTab> = persistentListOf(BookTab.All),
    val languages: ImmutableList<Language> = persistentListOf(),
    val availableBooks: ImmutableList<AudioBook> = persistentListOf(),
    val availableNewTestamentBooks: ImmutableList<AudioBook> = persistentListOf(),
    val availableOldTestamentBooks: ImmutableList<AudioBook> = persistentListOf(),
)