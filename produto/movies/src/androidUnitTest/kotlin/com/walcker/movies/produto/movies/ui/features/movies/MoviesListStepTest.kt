package com.walcker.movies.produto.movies.ui.features.movies

import com.google.testing.junit.testparameterinjector.TestParameter
import com.google.testing.junit.testparameterinjector.TestParameterInjector
import com.walcker.movies.produto.movies.features.ui.features.movies.MoviesListContent
import com.walcker.movies.produto.movies.features.ui.features.movies.MoviesListState
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
            MoviesListContent(
                state = params.state,
                strings = moviesListStringsPt,
                onPosterClick = { },
                onEvent = {},
            )
        }
    }

    enum class Params(
        val state: MoviesListState,
    ) {
        StateSuccess(
            state = MoviesListState.Success(movies = movieSectionTestData)
        ),
        StateLoading(
            state = MoviesListState.Loading
        ),
        StateError(
            state = MoviesListState.Error("Erro interno do servidor. Por favor, tente novamente mais tarde.")
        ),
    }
}