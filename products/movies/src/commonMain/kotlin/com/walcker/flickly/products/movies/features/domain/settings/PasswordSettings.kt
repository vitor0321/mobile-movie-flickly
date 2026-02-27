package com.walcker.flickly.products.movies.features.domain.settings

internal interface PasswordSettings {
    fun getSavedPassword(): String?
    fun savePassword(password: String)
    fun hasCustomPassword(): Boolean
}