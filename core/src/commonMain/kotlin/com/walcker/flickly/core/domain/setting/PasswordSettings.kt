package com.walcker.flickly.core.domain.setting

public interface PasswordSettings {
    fun getSavedPassword(): String?
    fun savePassword(password: String)
    fun hasCustomPassword(): Boolean
}