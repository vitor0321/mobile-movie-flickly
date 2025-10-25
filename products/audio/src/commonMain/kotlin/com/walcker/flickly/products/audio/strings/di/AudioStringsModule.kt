package com.walcker.flickly.products.audio.strings.di

import com.walcker.flickly.products.audio.strings.AudioStringsHolder
import org.koin.dsl.module

internal val audioStringsModule = module {
    single { AudioStringsHolder() }
}