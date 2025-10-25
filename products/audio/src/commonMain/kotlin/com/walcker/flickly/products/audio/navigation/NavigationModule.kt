package com.walcker.flickly.products.audio.navigation

import com.walcker.flickly.navigator.AudioEntry
import org.koin.dsl.module

internal val navigationModule = module {
    single<AudioEntry> { AudioEntryImpl() }
}