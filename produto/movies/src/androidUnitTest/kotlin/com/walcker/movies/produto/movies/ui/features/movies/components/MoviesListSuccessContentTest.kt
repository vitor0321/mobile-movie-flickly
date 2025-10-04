package com.walcker.movies.produto.movies.ui.features.movies.components

import com.walcker.movies.produto.movies.features.ui.features.movies.components.MoviesListSuccessContent
import com.walcker.movies.produto.movies.features.ui.preview.mockData.movieSectionTestData
import com.walcker.movies.produto.movies.strings.features.moviesListStringsPt
import com.walcker.movies.produto.movies.utils.DefaultPaparazzi
import com.walcker.movies.produto.movies.utils.movieSnapshot
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