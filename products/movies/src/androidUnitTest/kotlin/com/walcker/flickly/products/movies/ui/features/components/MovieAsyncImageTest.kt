package com.walcker.flickly.products.movies.ui.features.components

import com.walcker.flickly.cedarDS.MovieAsyncImage
import com.walcker.flickly.products.movies.ui.features.DefaultPaparazzi
import com.walcker.flickly.products.movies.features.ui.preview.mockData.movieTestData
import com.walcker.flickly.products.movies.ui.features.movieSnapshot
import org.junit.Rule
import kotlin.test.Test

internal class MovieAsyncImageTest {
    @get:Rule
    val paparazzi = DefaultPaparazzi

    @Test
    fun snapshot() {
        paparazzi.movieSnapshot {
            MovieAsyncImage(imageUrl = movieTestData.posterUrl)
        }
    }
}