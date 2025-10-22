package com.walcker.flickly.core.di

import com.walcker.movies.core.navigation.NavigatorHolder
import org.koin.dsl.module

internal val navigationModule = module {
    single { NavigatorHolder() }
}

