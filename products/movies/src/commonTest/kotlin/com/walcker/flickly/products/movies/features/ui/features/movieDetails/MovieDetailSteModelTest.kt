package com.walcker.flickly.products.movies.features.ui.features.movieDetails

import com.walcker.flickly.products.movies.features.domain.repository.MoviesRepository
import com.walcker.flickly.products.movies.mockData.domain.movieTestData1
import com.walcker.flickly.products.movies.mockFakes.FakeMoviesRepository
import com.walcker.flickly.products.movies.utils.CoroutineMainDispatcherTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
internal class MovieDetailSteModelTest : CoroutineMainDispatcherTestRule() {

    private fun createViewModel(
        moviesRepository: MoviesRepository
    ): MovieDetailsStepModel =
        MovieDetailsStepModel(
            moviesRepository = moviesRepository,
        )

    @Test
    fun `GIVEN a successful movie detail fetch WHEN init THEN uiState should be Loading`() = runTest(dispatcher) {
        // Given
        val moviesRepository = FakeMoviesRepository.createSuccessRepository()
        val stepModel = createViewModel(moviesRepository = moviesRepository)
        // Then
        val state = stepModel.state.first()

        assertTrue(state is MovieDetailsState.Loading)
    }

    @Test
    fun `GIVEN a successful movie detail fetch WHEN init THEN uiState should be Success`() = runTest(dispatcher) {
        // Given
        val moviesRepository = FakeMoviesRepository.createSuccessRepository()
        val stepModel = createViewModel(moviesRepository = moviesRepository)
        // When
        stepModel.onEvent(MovieDetailsInternalRoute.OnMovieDetailsData(123))
        // Then
        val state = stepModel.state.filter { it !is MovieDetailsState.Loading }.first()

        assertTrue(state is MovieDetailsState.Success)
        assertEquals(movieTestData1, state.movie)
    }

    @Test
    fun `GIVEN a failed movie detail fetch WHEN init THEN uiState should be Error`() = runTest(dispatcher) {
        // Given
        val errorMessage = "Algo deu errado. Tente novamente mais tarde."
        val moviesRepository = FakeMoviesRepository.createFailureRepository(RuntimeException(errorMessage))
        val stepModel = createViewModel(moviesRepository = moviesRepository)
        // When
        stepModel.onEvent(MovieDetailsInternalRoute.OnMovieDetailsData(123))
        // Then
        val state = stepModel.state.filter { it !is MovieDetailsState.Loading }.first()
        assertTrue(state is MovieDetailsState.Error)
        assertEquals(errorMessage, state.message)
    }
}