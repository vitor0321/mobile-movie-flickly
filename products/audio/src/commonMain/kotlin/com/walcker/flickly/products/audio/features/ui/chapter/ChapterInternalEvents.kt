package com.walcker.flickly.products.audio.features.ui.chapter

internal interface ChapterInternalEvents {
    data class OnError(val message: String) : ChapterInternalEvents
}

