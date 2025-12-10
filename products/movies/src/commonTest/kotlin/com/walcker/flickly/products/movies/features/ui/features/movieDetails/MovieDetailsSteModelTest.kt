package com.walcker.flickly.products.movies.features.ui.features.movieDetails

import com.walcker.flickly.products.movies.features.domain.repository.MoviesRepository
import com.walcker.flickly.products.movies.mockData.domain.movieTestData1
import com.walcker.flickly.products.movies.mockFakes.FakeMoviesRepository
import com.walcker.flickly.products.movies.strings.EnMoviesStrings
import com.walcker.flickly.products.movies.strings.MoviesStringsHolder
import com.walcker.flickly.products.movies.utils.CoroutineMainDispatcherTestRule
import com.walcker.flickly.core.navigation.NavigatorHolder
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.withTimeout
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertIs
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
internal class MovieDetailsSteModelTest : CoroutineMainDispatcherTestRule() {

    private fun createViewModel(
        moviesRepository: MoviesRepository,
    ): MovieDetailsStepModel {
        val stringsHolder = MoviesStringsHolder().apply { setStrings(EnMoviesStrings) }
        return MovieDetailsStepModel(
            moviesRepository = moviesRepository,
            stringsHolder = stringsHolder,
            navigatorHolder = NavigatorHolder()
        )
    }

    @Test
    fun `GIVEN a successful movie detail fetch WHEN init THEN uiState should be Loading`() = runTest(dispatcher) {
        // Given
        val moviesRepository = FakeMoviesRepository.createSuccessRepository()
        val stepModel = createViewModel(moviesRepository = moviesRepository)
        // Then
        val state = stepModel.state.first()
        assertTrue(state.loading)
    }

    @Test
    fun `GIVEN a successful movie detail fetch WHEN fetch THEN uiState should be Success`() = runTest(dispatcher) {
        // Given
        val moviesRepository = FakeMoviesRepository.createSuccessRepository()
        val stepModel = createViewModel(moviesRepository = moviesRepository)
        // When
        stepModel.onEvent(MovieDetailsInternalRoute.OnMovieDetailsData(123))
        runCurrent()
        // Then
        val successState = stepModel.state.filter { !it.loading && it.movie != null }.first()
        assertEquals(movieTestData1, successState.movie)
    }

    @Test
    fun `GIVEN a failed movie detail fetch WHEN fetch THEN uiState should be Error`() = runTest(dispatcher) {
        // Given
        val errorMessage = "Algo deu errado. Tente novamente mais tarde."
        val moviesRepository = FakeMoviesRepository.createFailureRepository(RuntimeException(errorMessage))
        val stepModel = createViewModel(moviesRepository = moviesRepository)
        // When
        stepModel.onEvent(MovieDetailsInternalRoute.OnMovieDetailsData(123))
        // Then
        val event = stepModel.events.first { it is MovieDetailsInternalEvents.OnError }

        assertIs<MovieDetailsInternalEvents.OnError>(event)
        assertEquals(errorMessage, event.errorMessage)
    }
}