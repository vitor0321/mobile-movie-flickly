package com.walcker.flickly.app

import com.walcker.flickly.core.di.coreModule
import com.walcker.flickly.products.audio.di.audioModule
import com.walcker.flickly.products.movies.di.moviesModule
import org.koin.core.module.Module

internal val appModule: List<Module> = coreModule + moviesModule + audioModule