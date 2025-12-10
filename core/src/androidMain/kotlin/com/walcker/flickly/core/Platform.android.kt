package com.walcker.flickly.core

import com.walcker.flickly.core.strings.Locales
import java.util.Locale

actual fun platformImpl(): Platform = AndroidPlatform()

private class AndroidPlatform : Platform {
    override val languageSystem: String = getSystemLanguage()
    override val accessToken: String = BuildConfig.TMDB_ACCESS_TOKEN.takeIf { it.isNotEmpty() }.orEmpty()
}

private fun getSystemLanguage(): String {
    val locale = Locale.getDefault()
    val language = locale.language

    return when {
        language.startsWith("pt", ignoreCase = true) -> Locales.PT
        language.startsWith("en", ignoreCase = true) -> Locales.EN
        language.startsWith("ur", ignoreCase = true) -> Locales.UR
        else -> Locales.EN
    }
}