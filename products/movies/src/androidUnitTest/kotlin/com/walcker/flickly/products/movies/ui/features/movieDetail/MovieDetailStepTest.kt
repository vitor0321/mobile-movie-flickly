package com.walcker.flickly.products.movies.ui.features.movieDetail

import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import com.walcker.flickly.products.movies.features.ui.features.movieDetails.MovieDetailContent
import com.walcker.flickly.products.movies.features.ui.features.movieDetails.MovieDetailsState
import com.walcker.flickly.products.movies.features.ui.preview.mockData.movieTestData
import com.walcker.flickly.products.movies.strings.movieDetailStringsPt
import com.walcker.flickly.products.movies.ui.features.DefaultPaparazzi
import com.walcker.flickly.products.movies.ui.features.movieSnapshot
import org.junit.Rule
import org.junit.runner.RunWith
import kotlin.test.Test

@RunWith(TestParameterInjector::class)
internal class MovieDetailStepTest {
    @get:Rule
    val paparazzi = DefaultPaparazzi

    @Test
    fun snapshot(@TestParameter params: Params) {
        paparazzi.movieSnapshot {
            MovieDetailContent(
                state = params.state,
                string = movieDetailStringsPt,
                onEvent = {},
            )
        }
    }

    enum class Params(
        val state: MovieDetailsState,
    ) {
        StateSuccess(
            state = MovieDetailsState(
                loading = false,
                movie = movieTestData
            )
        ),
        StateLoading(
            state = MovieDetailsState(loading = true)
        ),
    }
}