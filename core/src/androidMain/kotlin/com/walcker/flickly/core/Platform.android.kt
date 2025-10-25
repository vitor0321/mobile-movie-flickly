package com.walcker.flickly.core

import java.util.Locale

private const val LANG_EN = "en"
private const val LANG_PT = "pt"

actual fun platformImpl(): Platform = AndroidPlatform()

private class AndroidPlatform : Platform {
    override val languageSystem: String = getSystemLanguage()
    override val accessToken: String = BuildConfig.TMDB_ACCESS_TOKEN.takeIf { it.isNotEmpty() }.orEmpty()
}

private fun getSystemLanguage(): String {
    val locale = Locale.getDefault()
    val language = locale.language
    val country = locale.country

    println("🔍 Locale.getDefault().language: $language")
    println("🔍 Locale.getDefault().country: $country")
    println("🔍 Locale.getDefault().toString(): $locale")

    return when {
        language.startsWith("pt", ignoreCase = true) -> LANG_PT
        language.startsWith("en", ignoreCase = true) -> LANG_EN
        else -> LANG_EN // fallback
    }
}