package com.walcker.flickly.products.audio.navigation

import com.walcker.flickly.navigator.AudioDestination
import org.koin.dsl.module

internal val navigationModule = module {
    single<AudioDestination> { AudioDestinationImpl() }
}