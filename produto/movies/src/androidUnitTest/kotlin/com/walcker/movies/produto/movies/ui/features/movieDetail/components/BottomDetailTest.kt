package com.walcker.movies.produto.movies.ui.features.movieDetail.components

import com.walcker.movies.produto.movies.features.ui.features.movieDetails.components.BottomDetail
import com.walcker.movies.produto.movies.features.ui.preview.mockData.movieTestData
import com.walcker.movies.produto.movies.utils.DefaultPaparazzi
import com.walcker.movies.produto.movies.utils.movieSnapshot
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