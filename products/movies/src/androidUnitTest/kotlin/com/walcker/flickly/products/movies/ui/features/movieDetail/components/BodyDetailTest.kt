package com.walcker.flickly.products.movies.ui.features.movieDetail.components

import com.walcker.flickly.products.movies.features.ui.features.movieDetails.components.BodyDetail
import com.walcker.flickly.products.movies.features.ui.preview.mockData.movieTestData
import com.walcker.flickly.products.movies.strings.movieDetailStringsPt
import com.walcker.flickly.products.movies.ui.features.DefaultPaparazzi
import com.walcker.flickly.products.movies.ui.features.movieSnapshot
import org.junit.Rule
import kotlin.test.Test

internal class BodyDetailTest {
    @get:Rule
    val paparazzi = DefaultPaparazzi

    @Test
    fun snapshot() {
        paparazzi.movieSnapshot {
            BodyDetail(
                movie = movieTestData,
                string = movieDetailStringsPt,
                onWatchClick = { },
            )
        }
    }
}