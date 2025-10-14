package com.walcker.flickly.products.movies.ui.features.movieDetail.components

import com.walcker.flickly.products.movies.features.ui.features.movieDetails.components.BottomDetail
import com.walcker.flickly.products.movies.features.ui.preview.mockData.movieTestData
import com.walcker.flickly.products.movies.ui.features.DefaultPaparazzi
import com.walcker.flickly.products.movies.ui.features.movieSnapshot
import org.junit.Rule
import kotlin.test.Test

internal class BottomDetailTest {
    @get:Rule
    val paparazzi = DefaultPaparazzi

    @Test
    fun snapshot() {
        paparazzi.movieSnapshot {
            BottomDetail(
                movie = movieTestData,
            )
        }
    }
}