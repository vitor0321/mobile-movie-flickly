package com.walcker.movies.app

import com.walcker.movies.core.di.coreModule
import com.walcker.movies.produto.batSignal.di.audioModule
import com.walcker.movies.produto.movies.di.moviesModule
import org.koin.core.module.Module

internal val appModule: List<Module> = coreModule + moviesModule + audioModule