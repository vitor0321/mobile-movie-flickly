package com.walcker.flickly.core.data.setting

import com.russhwolf.settings.Settings
import com.walcker.flickly.core.domain.setting.PasswordSettings
import com.walcker.flickly.core.domain.setting.model.PasswordSettingsHolder.KEY_PASSWORD

internal class PasswordSettingsImpl(
    private val settings: Settings
) : PasswordSettings {

    override fun getSavedPassword(): String? = settings.getStringOrNull(KEY_PASSWORD)

    override fun savePassword(password: String) {
        settings.putString(KEY_PASSWORD, password)
    }

    override fun hasCustomPassword(): Boolean = settings.getStringOrNull(KEY_PASSWORD) != null
}