package com.walcker.flickly.products.movies.ui.features.movieDetail.components

import com.walcker.flickly.products.movies.features.ui.features.movieDetails.components.HeaderDetail
import com.walcker.flickly.products.movies.ui.features.DefaultPaparazzi
import com.walcker.flickly.products.movies.ui.features.movieSnapshot
import org.junit.Rule
import kotlin.test.Test

internal class HeaderDetailTest {
    @get:Rule
    val paparazzi = DefaultPaparazzi

    @Test
    fun snapshot() {
        paparazzi.movieSnapshot {
            HeaderDetail(
                posterUrl = "https://image.tmdb.org/t/p/w500/1LRLLWGvs5sZdTzuMqLEahb88Pc.jpg"
            )
        }
    }
}