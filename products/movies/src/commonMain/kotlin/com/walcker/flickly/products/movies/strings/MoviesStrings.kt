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

@LyricistStrings(languageTag = Locales.EN, default = true)
internal val EnMoviesStrings = MoviesStrings(
    appName = "Movies",
    moviesListStrings = moviesListStringsEn,
    movieDetailStrings = movieDetailStringsEn,
)

@LyricistStrings(languageTag = Locales.PT)
internal val PtMoviesStrings = MoviesStrings(
    appName = "Flickly",
    moviesListStrings = moviesListStringsPt,
    movieDetailStrings = movieDetailStringsPt,
)

@LyricistStrings(languageTag = Locales.UR)
internal val UrMoviesStrings = MoviesStrings(
    appName = "فلکلی",
    moviesListStrings = moviesListStringsUr,
    movieDetailStrings = movieDetailStringsUr,
)

@LyricistStrings(languageTag = Locales.SD_PK)
internal val SdPkMoviesStrings = MoviesStrings(
    appName = "فلکلي",
    moviesListStrings = moviesListStringsSdPk,
    movieDetailStrings = movieDetailStringsSdPk,
)

@LyricistStrings(languageTag = Locales.SD_IN)
internal val SdInMoviesStrings = MoviesStrings(
    appName = "फ्लिकली",
    moviesListStrings = moviesListStringsSdIn,
    movieDetailStrings = movieDetailStringsSdIn,
)

@LyricistStrings(languageTag = Locales.PA_PK)
internal val PaPkMoviesStrings = MoviesStrings(
    appName = "فلکلی",
    moviesListStrings = moviesListStringsPaPk,
    movieDetailStrings = movieDetailStringsPaPk,
)

@LyricistStrings(languageTag = Locales.PA_IN)
internal val PaInMoviesStrings = MoviesStrings(
    appName = "ਫਲਿਕਲੀ",
    moviesListStrings = moviesListStringsPaIn,
    movieDetailStrings = movieDetailStringsPaIn,
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
                Locales.SD_PK to SdPkMoviesStrings,
                Locales.SD_IN to SdInMoviesStrings,
                Locales.PA_PK to PaPkMoviesStrings,
                Locales.PA_IN to PaInMoviesStrings,
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