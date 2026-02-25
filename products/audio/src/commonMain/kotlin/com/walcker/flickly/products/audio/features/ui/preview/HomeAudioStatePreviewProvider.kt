package com.walcker.flickly.products.audio.features.ui.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.walcker.flickly.products.audio.features.domain.model.AudioBook
import com.walcker.flickly.products.audio.features.ui.home.HomeAudioState
import kotlinx.collections.immutable.toImmutableList

internal class HomeAudioStatePreviewProvider : PreviewParameterProvider<HomeAudioState> {
    override val values: Sequence<HomeAudioState> = sequenceOf(
        HomeAudioState(
            loading = false,
            selectedBook = AudioBook.Genesis,
            availableBooks = AudioBook.entries.toImmutableList(),
            availableNewTestamentBooks = AudioBook.entries.toImmutableList(),
            availableOldTestamentBooks = AudioBook.entries.toImmutableList(),
        ),
        HomeAudioState(loading = true)
    )
}