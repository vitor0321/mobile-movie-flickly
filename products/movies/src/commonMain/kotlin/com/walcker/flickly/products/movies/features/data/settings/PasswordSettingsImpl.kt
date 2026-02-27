package com.walcker.flickly.products.movies.features.data.settings

import com.russhwolf.settings.Settings
import com.walcker.flickly.products.movies.features.data.settings.PasswordSettingsHolder.KEY
import com.walcker.flickly.products.movies.features.domain.settings.PasswordSettings

internal class PasswordSettingsImpl(
    private val settings: Settings
) : PasswordSettings {

    override fun getSavedPassword(): String? = settings.getStringOrNull(KEY)

    override fun savePassword(password: String) {
        settings.putString(KEY, password)
    }

    override fun hasCustomPassword(): Boolean = settings.getStringOrNull(KEY) != null
}

internal object PasswordSettingsHolder {
    const val KEY = "user_password"
    const val REQUIRED_PASSWORD = "2580456"
    const val MIN_SIZE = 5
    const val MAX_SIZE = 10
}