package com.walcker.movies.produto.movies.ui.features.movieDetail.components

import com.walcker.movies.produto.movies.features.ui.features.movieDetails.components.BodyDetail
import com.walcker.movies.produto.movies.features.ui.preview.mockData.movieTestData
import com.walcker.movies.produto.movies.strings.features.movieDetailStringsPt
import com.walcker.movies.produto.movies.utils.DefaultPaparazzi
import com.walcker.movies.produto.movies.utils.movieSnapshot
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