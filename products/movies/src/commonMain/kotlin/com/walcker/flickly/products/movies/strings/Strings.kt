package com.walcker.flickly.products.movies.strings

import androidx.compose.runtime.staticCompositionLocalOf
import cafe.adriel.lyricist.LyricistStrings
import com.walcker.flickly.products.movies.strings.features.MovieDetailString
import com.walcker.flickly.products.movies.strings.features.MoviesListStrings
import com.walcker.flickly.products.movies.strings.features.movieDetailStringsEn
import com.walcker.flickly.products.movies.strings.features.movieDetailStringsPt
import com.walcker.flickly.products.movies.strings.features.moviesListStringsEn
import com.walcker.flickly.products.movies.strings.features.moviesListStringsPt

internal val LocalStrings = staticCompositionLocalOf { EnStrings }

data class MoviesStrings(
    val appName: String,
    val moviesListStrings: MoviesListStrings,
    val movieDetailStrings: MovieDetailString,
)

@LyricistStrings(languageTag = Locales.EN, default = true)
internal val EnStrings = MoviesStrings(
    appName = "Movies",
    moviesListStrings = moviesListStringsEn,
    movieDetailStrings = movieDetailStringsEn,
)

@LyricistStrings(languageTag = Locales.PT)
internal val PtStrings = MoviesStrings(
    appName = "Flickly",
    moviesListStrings = moviesListStringsPt,
    movieDetailStrings = movieDetailStringsPt,
)

internal object Locales {
    const val EN = "en"
    const val PT = "pt"
}