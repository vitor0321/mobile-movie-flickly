package com.walcker.flickly.products.audio.features.ui.home

import com.walcker.flickly.products.audio.features.domain.model.AudioBook

internal interface HomeAudioInternalRoute {
    data object OnRetry : HomeAudioInternalRoute
    data object OnPopBackStack : HomeAudioInternalRoute
    data class OnSelectBook(val audioBook: AudioBook) : HomeAudioInternalRoute
    data class OnSelectChapter(val chapter: Int) : HomeAudioInternalRoute
}