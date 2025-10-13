package com.walcker.movies.produto.movies.features.ui.features.home

import com.walcker.movies.produto.movies.features.domain.models.MovieSection
import com.walcker.movies.produto.movies.features.domain.repository.MoviesRepository
import com.walcker.movies.produto.movies.mockFakes.FakeMoviesRepository
import com.walcker.movies.produto.movies.utils.CoroutineMainDispatcherTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
internal class HomeStepModelTest : CoroutineMainDispatcherTestRule() {

    private fun createViewModel(
        moviesRepository: MoviesRepository
    ): HomeMoviesStepModel = HomeMoviesStepModel(moviesRepository)

    @Test
    fun `GIVEN a successful list fetch WHEN init THEN uiState should be Loading`() = runTest(dispatcher) {
        val stepModel = createViewModel(FakeMoviesRepository.createSuccessRepository())
        val uiState = stepModel.state.first()
        assertTrue(uiState is HomeMoviesState.Loading)
    }

    @Test
    fun `GIVEN a successful list fetch WHEN init completes THEN uiState should be Success`() = runTest(dispatcher) {
        val stepModel = createViewModel(FakeMoviesRepository.createSuccessRepository())
        val uiState = stepModel.state.filter { it !is HomeMoviesState.Loading }.first()
        assertTrue(uiState is HomeMoviesState.Success)
        val movies = uiState.movies
        assertTrue(movies.isNotEmpty())
        assertTrue(movies.first().movies.first().title.isNotBlank())
    }

    @Test
    fun `GIVEN next page load WHEN loadNextPage is called THEN movies list accumulates`() = runTest(dispatcher) {
        val stepModel = createViewModel(FakeMoviesRepository.createSuccessRepository())
        // First Call
        val initialSuccess = stepModel.state.filter { it is HomeMoviesState.Success }
            .first() as HomeMoviesState.Success

        // Paging
        val popular = initialSuccess.movies.first { it.sectionType == MovieSection.SectionType.POPULAR }
        val initialCount = popular.movies.size

        stepModel.onEvent(HomeMoviesInternalRoute.OnLoadNextPage(MovieSection.SectionType.POPULAR))

        val nextSuccess = stepModel.state.filter { state ->
            state is HomeMoviesState.Success &&
                    state.movies.first { it.sectionType == MovieSection.SectionType.POPULAR }
                        .movies.size > initialCount
        }.first() as HomeMoviesState.Success

        val newPopular = nextSuccess.movies.first { it.sectionType == MovieSection.SectionType.POPULAR }
        assertTrue(newPopular.movies.size > initialCount)
    }

    @Test
    fun `GIVEN a failed movies list fetch WHEN init THEN uiState should be Error`() = runTest(dispatcher) {
        // Given
        val errorMessage = "Algo deu errado. Tente novamente mais tarde."
        val moviesRepository = FakeMoviesRepository.createFailureRepository(RuntimeException("Repository error"))
        val stepModel = createViewModel(moviesRepository = moviesRepository)

        // Then
        val state = stepModel.state.filter { it !is HomeMoviesState.Loading }.first()
        assertTrue(state is HomeMoviesState.Error)
        assertEquals(errorMessage, state.message)
    }

    @Test
    fun `WHEN loadNextPage called THEN only that section is updated and Success state is emitted`() = runTest(dispatcher) {
        // Given
        val stepModel = createViewModel(FakeMoviesRepository.createSuccessRepository())
        stepModel.state.filter { it is HomeMoviesState.Success }.first()

        // When
        stepModel.onEvent(HomeMoviesInternalRoute.OnLoadNextPage(MovieSection.SectionType.POPULAR))

        // Then
        val state = stepModel.state.filter { it is HomeMoviesState.Success }.first()
        assertTrue(state is HomeMoviesState.Success)

        val sections = state.movies
        val updatedSection = sections.first { it.sectionType == MovieSection.SectionType.POPULAR }
        assertEquals(5, updatedSection.movies.size)
    }

    @Test
    fun `WHEN loadNextPage called with error THEN emits Error state`() = runTest(dispatcher) {
        // Given
        val stepModel = HomeMoviesStepModel(FakeMoviesRepository.createFailureRepository())

        // When
        stepModel.onEvent(HomeMoviesInternalRoute.OnLoadNextPage(MovieSection.SectionType.TOP_RATED))

        // Then
        val state = stepModel.state.filter { it is HomeMoviesState.Error }.first()
        assertTrue(state is HomeMoviesState.Error)
    }
}
