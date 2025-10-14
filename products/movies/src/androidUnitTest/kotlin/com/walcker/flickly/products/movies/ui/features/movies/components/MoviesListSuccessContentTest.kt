package com.walcker.flickly.products.movies.ui.features.movies.components

import com.walcker.flickly.products.movies.features.ui.features.home.components.MoviesListSuccessContent
import com.walcker.flickly.products.movies.features.ui.preview.mockData.movieSectionTestData
import com.walcker.flickly.products.movies.strings.features.moviesListStringsPt
import com.walcker.flickly.products.movies.ui.features.DefaultPaparazzi
import com.walcker.flickly.products.movies.ui.features.movieSnapshot
import org.junit.Rule
import kotlin.test.Test

internal class MoviesListSuccessContentTest {
    @get:Rule
    val paparazzi = DefaultPaparazzi

    @Test
    fun snapshot() {
        paparazzi.movieSnapshot {
            MoviesListSuccessContent(
                strings = moviesListStringsPt,
                movies = movieSectionTestData,
                onPosterClick = {},
                onLoadMore = {}
            )
        }
    }
}