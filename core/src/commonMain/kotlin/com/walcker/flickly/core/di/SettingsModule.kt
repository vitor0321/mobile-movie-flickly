package com.walcker.flickly.core.di

import com.russhwolf.settings.Settings
import com.walcker.flickly.core.domain.setting.PasswordSettings
import com.walcker.flickly.core.data.setting.PasswordSettingsImpl
import org.koin.dsl.module

internal val settingsModule = module {
    single { Settings() }
    single<PasswordSettings> { PasswordSettingsImpl(settings = get()) }
}