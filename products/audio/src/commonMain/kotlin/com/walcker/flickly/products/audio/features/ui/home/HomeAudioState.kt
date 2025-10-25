package com.walcker.flickly.products.audio.features.ui.home

import com.walcker.flickly.products.audio.features.domain.model.AudioBook
import com.walcker.flickly.products.audio.strings.AudioHomeStrings
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

internal data class HomeAudioState(
    val loading: Boolean = true,
    val audioUrl: String? = null,
    val strings: AudioHomeStrings = AudioHomeStrings(),
    val selectedBook: AudioBook = AudioBook.MATTHEW,
    val selectedChapter: Int = 1,
    val availableBooks: ImmutableList<AudioBook> = persistentListOf(),
    val availableChapters: ImmutableList<Int> = persistentListOf(),
)