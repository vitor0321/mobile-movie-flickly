package com.walcker.flickly.products.movies.ui.features.components

import com.walcker.flickly.cedarDS.MovieBadge
import com.walcker.flickly.products.movies.ui.features.DefaultPaparazzi
import com.walcker.flickly.products.movies.ui.features.movieSnapshot
import org.junit.Rule
import kotlin.test.Test

internal class MovieInfoItemTest {
    @get:Rule
    val paparazzi = DefaultPaparazzi

    @Test
    fun snapshot() {
        paparazzi.movieSnapshot {
            MovieBadge(text = "Action")
        }
    }
}