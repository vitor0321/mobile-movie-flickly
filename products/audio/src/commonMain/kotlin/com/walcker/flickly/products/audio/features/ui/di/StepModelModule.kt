package com.walcker.flickly.products.audio.features.ui.di

import com.walcker.flickly.products.audio.features.ui.home.HomeAudioStepModel
import org.koin.dsl.module

internal val stepModelModule = module {
    factory {
        HomeAudioStepModel(
            audioManager = get(),
            navigatorHolder = get(),
        )
    }
}