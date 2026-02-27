package com.walcker.flickly.products.movies.features.data.di

import com.russhwolf.settings.Settings
import com.walcker.flickly.products.movies.features.domain.settings.PasswordSettings
import com.walcker.flickly.products.movies.features.data.settings.PasswordSettingsImpl
import org.koin.dsl.module

internal val settingsModule = module {
    single { Settings() }
    single<PasswordSettings> { PasswordSettingsImpl(settings = get()) }
}