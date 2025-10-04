package com.walcker.movies.produto.movies.ui.components

import com.walcker.movies.produto.movies.features.ui.components.MoviePoster
import com.walcker.movies.produto.movies.features.ui.preview.mockData.movieTestData
import com.walcker.movies.produto.movies.utils.DefaultPaparazzi
import com.walcker.movies.produto.movies.utils.movieSnapshot
import org.junit.Rule
import kotlin.test.Test

internal class MoviePosterTest {
    @get:Rule
    val paparazzi = DefaultPaparazzi

    @Test
    fun snapshot() {
        paparazzi.movieSnapshot {
            MoviePoster(
                movie = movieTestData,
                onPosterClick = {}
            )
        }
    }
}