package com.walcker.flickly.products.movies.ui.features.movies

import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import com.walcker.flickly.products.movies.features.ui.features.home.HomeContent
import com.walcker.flickly.products.movies.features.ui.features.home.HomeMoviesState
import com.walcker.flickly.products.movies.features.ui.preview.mockData.movieSectionTestData
import com.walcker.flickly.products.movies.strings.moviesListStringsPt
import com.walcker.flickly.products.movies.ui.features.DefaultPaparazzi
import com.walcker.flickly.products.movies.ui.features.movieSnapshot
import org.junit.Rule
import org.junit.runner.RunWith
import kotlin.test.Test

@RunWith(TestParameterInjector::class)
internal class MoviesListStepTest {
    @get:Rule
    val paparazzi = DefaultPaparazzi

    @Test
    fun snapshot(@TestParameter params: Params) {
        paparazzi.movieSnapshot {
            HomeContent(
                state = params.state,
                strings = moviesListStringsPt,
                onEvent = {},
            )
        }
    }

    enum class Params(
        val state: HomeMoviesState,
    ) {
        StateSuccess(
            state = HomeMoviesState(
                loading = false,
                movies = movieSectionTestData
            )
        ),
        StateLoading(
            state = HomeMoviesState(loading = true)
        ),
        StateError(
            state = HomeMoviesState(
                loading = false,
                errorMessage = "Erro interno do servidor. Por favor, tente novamente mais tarde."
            )
        ),
    }
}