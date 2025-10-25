package com.walcker.flickly.products.movies.strings

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import cafe.adriel.lyricist.Lyricist
import cafe.adriel.lyricist.LyricistStrings
import com.walcker.flickly.core.strings.GenericStringsHolder
import com.walcker.flickly.core.strings.Locales
import com.walcker.flickly.core.strings.createLyricist

internal data class MoviesStrings(
    val appName: String,
    val moviesListStrings: MoviesListStrings,
    val movieDetailStrings: MovieDetailStrings,
)

@LyricistStrings(languageTag = Locales.EN)
internal val EnMoviesStrings = MoviesStrings(
    appName = "Movies",
    moviesListStrings = moviesListStringsEn,
    movieDetailStrings = movieDetailStringsEn,
)

@LyricistStrings(languageTag = Locales.PT, default = true)
internal val PtMoviesStrings = MoviesStrings(
    appName = "Flickly",
    moviesListStrings = moviesListStringsPt,
    movieDetailStrings = movieDetailStringsPt,
)

@LyricistStrings(languageTag = Locales.UR, default = true)
internal val UrMoviesStrings = MoviesStrings(
    appName = "Flickly",
    moviesListStrings = moviesListStringsUr,
    movieDetailStrings = movieDetailStringsUr,
)

internal val LocalMoviesStrings = staticCompositionLocalOf { EnMoviesStrings }

internal class MoviesStringsHolder : GenericStringsHolder<MoviesStrings>()

@Composable
internal fun ProvideMoviesStrings(
    lyricist: Lyricist<MoviesStrings>,
    content: @Composable () -> Unit
) {
    key(lyricist.languageTag) {
        CompositionLocalProvider(LocalMoviesStrings provides lyricist.strings) {
            content()
        }
    }
}

@Composable
internal fun rememberMoviesStrings(
    languageTag: String,
): Lyricist<MoviesStrings> {
    val lyricist = remember {
        createLyricist(
            defaultLanguageTag = languageTag,
            translations = mapOf(
                Locales.EN to EnMoviesStrings,
                Locales.PT to PtMoviesStrings,
                Locales.UR to UrMoviesStrings,
            ),
        )
    }

    LaunchedEffect(languageTag) {
        if (lyricist.languageTag != languageTag) {
            lyricist.languageTag = languageTag
        }
    }
    return lyricist
}