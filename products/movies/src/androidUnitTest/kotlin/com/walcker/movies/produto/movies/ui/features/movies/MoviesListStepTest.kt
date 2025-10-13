package com.walcker.movies.produto.movies.ui.features.movies

import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import com.walcker.movies.produto.movies.features.ui.features.home.HomeContent
import com.walcker.movies.produto.movies.features.ui.features.home.HomeMoviesState
import com.walcker.movies.produto.movies.features.ui.preview.mockData.movieSectionTestData
import com.walcker.movies.produto.movies.strings.features.moviesListStringsPt
import com.walcker.movies.produto.movies.utils.DefaultPaparazzi
import com.walcker.movies.produto.movies.utils.movieSnapshot
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
                onPosterClick = { },
                onEvent = {},
            )
        }
    }

    enum class Params(
        val state: HomeMoviesState,
    ) {
        StateSuccess(
            state = HomeMoviesState.Success(movies = movieSectionTestData)
        ),
        StateLoading(
            state = HomeMoviesState.Loading
        ),
        StateError(
            state = HomeMoviesState.Error("Erro interno do servidor. Por favor, tente novamente mais tarde.")
        ),
    }
}