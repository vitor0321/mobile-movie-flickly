package com.walcker.flickly.products.movies.ui.features.components

import com.walcker.flickly.cedarDS.MovieDotsIndicator
import com.walcker.flickly.products.movies.ui.features.DefaultPaparazzi
import com.walcker.flickly.products.movies.ui.features.movieSnapshot
import org.junit.Rule
import kotlin.test.Test

internal class MovieDotsIndicatorTest {

    @get:Rule
    val paparazzi = DefaultPaparazzi

    @Test
    fun snapshot() {
        paparazzi.movieSnapshot {
            MovieDotsIndicator(
                count = 5,
                selectedIndex = 2
            )
        }
    }
}