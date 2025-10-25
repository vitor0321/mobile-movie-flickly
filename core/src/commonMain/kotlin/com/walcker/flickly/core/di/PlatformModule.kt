package com.walcker.flickly.core.di

import com.walcker.flickly.core.Platform
import com.walcker.flickly.core.platformImpl
import org.koin.dsl.module

internal val platformModule = module {
    single<Platform> { platformImpl() }
}