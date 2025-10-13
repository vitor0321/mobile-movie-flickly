package com.walcker.movies.app

import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import kotlin.concurrent.Volatile

@Volatile
private var koinStarted = false


private var koinAppRef: KoinApplication? = null

@Suppress("unused")
public fun startKoinIfNeeded(): KoinApplication {
    koinAppRef?.let { return it }
    val created = startKoin {
        modules(appModule)
    }
    koinAppRef = created
    return created
}
