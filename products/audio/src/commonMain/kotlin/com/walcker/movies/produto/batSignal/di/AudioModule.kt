package com.walcker.movies.produto.batSignal.di

import com.walcker.movies.produto.batSignal.navigation.navigationModule
import org.koin.core.module.Module

public val audioModule = listOf<Module>(

    // Navigator
    navigationModule,
)