package com.walcker.movies.app

import org.koin.core.context.startKoin
import kotlin.concurrent.Volatile

@Volatile
private var koinStarted = false

@Suppress("unused")
fun startKoinIfNeeded() {
    if (!koinStarted) {
        try {
            startKoin { modules(appModule) }
        } catch (_: IllegalStateException) {
        }
        koinStarted = true
    }
}
