package com.walcker.flickly.products.audio.features.ui.chapter

import com.walcker.flickly.products.audio.features.domain.model.AudioBook
import com.walcker.flickly.products.audio.features.domain.model.BookTab
import com.walcker.flickly.products.audio.features.domain.model.Language
import com.walcker.flickly.products.audio.strings.BibleBooksStrings
import com.walcker.flickly.products.audio.strings.ChapterStrings
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

internal data class ChapterState(
    val loading: Boolean = false,
    val selectedBook: AudioBook = AudioBook.Matthew,
    val selectedChapter: Int = 1,
    val selectedLanguage: Language = Language.URDU,
    val testament: BookTab = BookTab.NewTestament,
    val availableChapters: ImmutableList<Int> = persistentListOf(),
    val audioUrl: String? = null,
    val strings: ChapterStrings = ChapterStrings(),
    val bibleBooksStrings: BibleBooksStrings = BibleBooksStrings(),
)
