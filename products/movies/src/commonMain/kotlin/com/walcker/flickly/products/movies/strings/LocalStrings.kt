package com.walcker.flickly.products.movies.strings

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import cafe.adriel.lyricist.Lyricist

private fun createLyricist(
    defaultLanguageTag: String = Locales.EN,
): Lyricist<MoviesStrings> =
    Lyricist(
        defaultLanguageTag = defaultLanguageTag,
        translations = mapOf(
            Locales.EN to EnStrings,
            Locales.PT to PtStrings
        )
    )

@Composable
internal fun rememberStrings(
    defaultLanguageTag: String = Locales.EN,
    currentLanguageTag: String = defaultLanguageTag
): Lyricist<MoviesStrings> =
    remember(currentLanguageTag) {
        createLyricist(
            defaultLanguageTag = defaultLanguageTag
        ).apply {
            languageTag = currentLanguageTag
        }
    }