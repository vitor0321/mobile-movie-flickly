package com.walcker.flickly.products.movies.di

import com.walcker.flickly.products.movies.features.data.di.networkModule
import com.walcker.flickly.products.movies.features.data.di.repositoryModule
import com.walcker.flickly.products.movies.features.ui.di.stepModelModule
import com.walcker.flickly.products.movies.navigation.navigationModule
import com.walcker.flickly.products.movies.strings.di.stringsModule
import org.koin.core.module.Module

public val moviesModule = listOf<Module>(
    // Data
    networkModule,
    repositoryModule,

    // UI
    stepModelModule,

    // Navigator
    navigationModule,

    // Strings
    stringsModule,
)