package com.walcker.flickly.app

import org.koin.core.KoinApplication
import org.koin.core.context.startKoin

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
