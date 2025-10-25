package com.walcker.flickly.app

import android.app.Application

class AppApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoinIfNeeded()
    }
}