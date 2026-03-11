package com.walcker.flickly.core.data.setting

import com.russhwolf.settings.Settings
import com.walcker.flickly.core.domain.setting.PasswordSettings
import com.walcker.flickly.core.domain.setting.model.PasswordSettingsHolder.KEY_PASSWORD
import com.walcker.flickly.core.domain.setting.model.PasswordSettingsHolder.REQUIRED_PASSWORD
import org.kotlincrypto.hash.sha2.SHA256

internal class PasswordSettingsImpl(
    private val settings: Settings
) : PasswordSettings {

    override fun getSavedPassword(): String? = settings.getStringOrNull(KEY_PASSWORD)

    override fun savePassword(password: String) {
        settings.putString(KEY_PASSWORD, password.sha256())
    }

    override fun hasCustomPassword(): Boolean = settings.getStringOrNull(KEY_PASSWORD) != null

    override fun verifyPassword(password: String): Boolean {
        val stored = settings.getStringOrNull(KEY_PASSWORD)
        return if (stored == null) password == REQUIRED_PASSWORD
        else password.sha256() == stored
    }

    private fun String.sha256(): String {
        val digest = SHA256()
        digest.update(encodeToByteArray())
        return digest.digest().joinToString("") { byte: Byte ->
            (byte.toInt() and 0xFF).toString(16).padStart(2, '0')
        }
    }

}