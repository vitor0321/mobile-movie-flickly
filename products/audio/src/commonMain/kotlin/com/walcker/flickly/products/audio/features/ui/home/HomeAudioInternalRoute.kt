package com.walcker.flickly.products.audio.features.ui.home

import com.walcker.flickly.products.audio.features.domain.model.AudioBook
import com.walcker.flickly.products.audio.features.domain.model.BookTab
import com.walcker.flickly.products.audio.features.domain.model.Language

internal interface HomeAudioInternalRoute {
    data object OnRetry : HomeAudioInternalRoute
    data object OnPopBackStack : HomeAudioInternalRoute
    data object OnShowChangePassword : HomeAudioInternalRoute
    data class OnChangePassword(val newPassword: String) : HomeAudioInternalRoute
    data class OnSelectBook(val audioBook: AudioBook) : HomeAudioInternalRoute
    data class OnSelectLanguage(val language: Language) : HomeAudioInternalRoute
    data class OnSelectTab(val tab: BookTab) : HomeAudioInternalRoute
}