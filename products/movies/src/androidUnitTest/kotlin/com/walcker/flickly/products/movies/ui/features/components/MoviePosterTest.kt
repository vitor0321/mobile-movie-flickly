package com.walcker.flickly.products.movies.ui.features.components

import com.walcker.flickly.cedarDS.MoviePoster
import com.walcker.flickly.products.movies.features.ui.preview.mockData.movieTestData
import com.walcker.flickly.products.movies.ui.features.DefaultPaparazzi
import com.walcker.flickly.products.movies.ui.features.movieSnapshot
import org.junit.Rule
import kotlin.test.Test

internal class MoviePosterTest {
    @get:Rule
    val paparazzi = DefaultPaparazzi

    @Test
    fun snapshot() {
        paparazzi.movieSnapshot {
            MoviePoster(
                title = movieTestData.title,
                imageUrl = movieTestData.posterUrl,
                onPosterClick = {}
            )
        }
    }
}