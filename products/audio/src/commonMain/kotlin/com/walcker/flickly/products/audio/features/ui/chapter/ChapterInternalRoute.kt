package com.walcker.flickly.products.audio.features.ui.chapter

import com.walcker.flickly.products.audio.features.domain.model.AudioBook
import com.walcker.flickly.products.audio.features.domain.model.BookTab
import com.walcker.flickly.products.audio.features.domain.model.Language

internal interface ChapterInternalRoute {
    data object OnPopBackStack : ChapterInternalRoute
    data object OnRetryAudio : ChapterInternalRoute
    data class OnLoad(
        val language: Language,
        val book: AudioBook,
        val testament: BookTab,
    ) : ChapterInternalRoute
    data class OnSelectChapter(val chapter: Int) : ChapterInternalRoute
}
