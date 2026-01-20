package com.walcker.flickly.core.di

import com.walcker.flickly.core.data.firebase.FirebaseStorageService
import com.walcker.flickly.core.data.firebase.FirebaseStorageServiceImpl
import org.koin.dsl.module

internal val firebaseModule = module {
    single<FirebaseStorageService> { FirebaseStorageServiceImpl() }
}