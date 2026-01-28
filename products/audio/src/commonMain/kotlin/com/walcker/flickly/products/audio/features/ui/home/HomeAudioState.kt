package com.walcker.flickly.products.audio.features.ui.home

import com.walcker.flickly.products.audio.features.domain.model.AudioBook
import com.walcker.flickly.products.audio.strings.AudioHomeStrings
import com.walcker.flickly.products.audio.strings.BibleBooksStrings
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

internal data class HomeAudioState(
    val loading: Boolean = false,
    val audioUrl: String? = null,
    val strings: AudioHomeStrings = AudioHomeStrings(),
    val bibleBooksStrings: BibleBooksStrings = BibleBooksStrings(),
    val selectedBook: AudioBook = AudioBook.Matthew,
    val selectedChapter: Int = 1,
    val availableBooks: ImmutableList<AudioBook> = persistentListOf(),
    val availableNewTestamentBooks: ImmutableList<AudioBook> = persistentListOf(),
    val availableOldTestamentBooks: ImmutableList<AudioBook> = persistentListOf(),
    val availableChapters: ImmutableList<Int> = persistentListOf(),
)