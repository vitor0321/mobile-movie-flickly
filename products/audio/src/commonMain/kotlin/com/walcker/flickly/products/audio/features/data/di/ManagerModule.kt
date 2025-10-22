package com.walcker.flickly.products.audio.features.data.di

import com.walcker.flickly.core.di.Dispatcher
import com.walcker.flickly.products.audio.features.data.manager.AudioManagerImpl
import com.walcker.flickly.products.audio.features.domain.manager.AudioManager
import org.koin.core.qualifier.named
import org.koin.dsl.module

internal val managerModule = module {
    factory<AudioManager> {
        AudioManagerImpl(
            storageService = get(),
            dispatcher = get(named(Dispatcher.IO))
        )
    }
}