package com.walcker.flickly.products.movies.strings.di

import com.walcker.flickly.products.movies.strings.StringsHolder
import org.koin.dsl.module

internal val stringsModule = module {
    single { StringsHolder() }
}

