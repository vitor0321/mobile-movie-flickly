package com.walcker.flickly.products.audio.features.ui.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.walcker.flickly.products.audio.features.domain.model.AudioBook
import com.walcker.flickly.products.audio.features.ui.home.HomeAudioState
import kotlinx.collections.immutable.toImmutableList

internal class HomeAudioStatePreviewProvider : PreviewParameterProvider<HomeAudioState> {
    override val values: Sequence<HomeAudioState> = sequenceOf(
        HomeAudioState(
            loading = false,
            audioUrl = "https://www.bibletalk.tv/audio/genesis/01.mp3",
            selectedBook = AudioBook.Genesis,
            selectedChapter = 1,
            availableBooks = AudioBook.entries.toImmutableList(),
            availableNewTestamentBooks = AudioBook.entries.toImmutableList(),
            availableOldTestamentBooks = AudioBook.entries.toImmutableList(),
            availableChapters = (1..AudioBook.Genesis.totalChapters).toList().toImmutableList()
        ),
        HomeAudioState(loading = true)
    )
}