package com.walcker.movies.produto.movies.ui.features.movieDetail.components

import com.walcker.movies.produto.movies.features.ui.features.movieDetails.components.HeaderDetail
import com.walcker.movies.produto.movies.utils.DefaultPaparazzi
import com.walcker.movies.produto.movies.utils.movieSnapshot
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