package com.walcker.flickly.core.di

import com.walcker.flickly.core.data.firebase.DefaultFirebaseStorageService
import com.walcker.flickly.core.data.firebase.FirebaseStorageService
import org.koin.dsl.module

internal val firebaseModule = module {

    single<FirebaseStorageService> {
        DefaultFirebaseStorageService()
    }
}