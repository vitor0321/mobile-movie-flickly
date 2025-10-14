package com.walcker.flickly.products.audio.navigation

import com.walcker.flickly.navigator.BatSignalEntry
import org.koin.dsl.module

internal val navigationModule = module {
    single<BatSignalEntry> { AudioEntryImpl() }
}