package com.walcker.movies.produto.movies.di

import com.walcker.movies.produto.movies.features.data.di.networkModule
import com.walcker.movies.produto.movies.features.data.di.repositoryModule
import com.walcker.movies.produto.movies.features.ui.di.stepModelModule
import com.walcker.movies.produto.movies.navigation.navigationModule
import org.koin.core.module.Module

public val moviesModule = listOf<Module>(
    // Data
    networkModule,
    repositoryModule,

    // UI
    stepModelModule,

    // Navigator
    navigationModule,
)