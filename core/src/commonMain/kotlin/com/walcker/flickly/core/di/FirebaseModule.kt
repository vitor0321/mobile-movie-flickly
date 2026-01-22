package com.walcker.flickly.core.di

import com.walcker.flickly.core.data.firebase.FirebaseStorageServiceImpl
import com.walcker.flickly.core.domain.firebase.FirebaseStorageService
import org.koin.dsl.module

internal val firebaseModule = module {
    single<FirebaseStorageService> { FirebaseStorageServiceImpl() }
}