package com.walcker.flickly.products.audio.features.ui.di

import com.walcker.flickly.products.audio.features.ui.chapter.ChapterStepModel
import com.walcker.flickly.products.audio.features.ui.home.HomeAudioStepModel
import org.koin.dsl.module

internal val stepModelModule = module {
    single {
        HomeAudioStepModel(
            audioManager = get(),
            stringsHolder = get(),
            navigatorHolder = get(),
        )
    }
    factory {
        ChapterStepModel(
            audioManager = get(),
            stringsHolder = get(),
            navigatorHolder = get(),
        )
    }
}