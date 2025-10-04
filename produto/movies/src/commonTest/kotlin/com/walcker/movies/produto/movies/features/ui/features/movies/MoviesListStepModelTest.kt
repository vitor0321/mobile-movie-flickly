package com.walcker.movies.produto.movies.features.ui.features.movies

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
internal class MoviesListStepModelTest : CoroutineMainDispatcherTestRule() {

    private fun createViewModel(
        moviesRepository: MoviesRepository
    ): MoviesListStepModel = MoviesListStepModel(moviesRepository)

    @Test
    fun `GIVEN a successful list fetch WHEN init THEN uiState should be Loading`() = runTest(dispatcher) {
        val stepModel = createViewModel(FakeMoviesRepository.createSuccessRepository())
        val uiState = stepModel.state.first()
        assertTrue(uiState is MoviesListState.Loading)
    }

    @Test
    fun `GIVEN a successful list fetch WHEN init completes THEN uiState should be Success`() = runTest(dispatcher) {
        val stepModel = createViewModel(FakeMoviesRepository.createSuccessRepository())
        val uiState = stepModel.state.filter { it !is MoviesListState.Loading }.first()
        assertTrue(uiState is MoviesListState.Success)
        val movies = uiState.movies
        assertTrue(movies.isNotEmpty())
        assertTrue(movies.first().movies.first().title.isNotBlank())
    }

    @Test
    fun `GIVEN next page load WHEN loadNextPage is called THEN movies list accumulates`() = runTest(dispatcher) {
        val stepModel = createViewModel(FakeMoviesRepository.createSuccessRepository())
        // First Call
        val initialSuccess = stepModel.state.filter { it is MoviesListState.Success }
            .first() as MoviesListState.Success

        // Paging
        val popular = initialSuccess.movies.first { it.sectionType == MovieSection.SectionType.POPULAR }
        val initialCount = popular.movies.size

        stepModel.onEvent(MoviesListInternalRoute.OnLoadNextPage(MovieSection.SectionType.POPULAR))

        val nextSuccess = stepModel.state.filter { state ->
            state is MoviesListState.Success &&
                    state.movies.first { it.sectionType == MovieSection.SectionType.POPULAR }
                        .movies.size > initialCount
        }.first() as MoviesListState.Success

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
        val state = stepModel.state.filter { it !is MoviesListState.Loading }.first()
        assertTrue(state is MoviesListState.Error)
        assertEquals(errorMessage, state.message)
    }

    @Test
    fun `WHEN loadNextPage called THEN only that section is updated and Success state is emitted`() = runTest(dispatcher) {
        // Given
        val stepModel = createViewModel(FakeMoviesRepository.createSuccessRepository())
        stepModel.state.filter { it is MoviesListState.Success }.first()

        // When
        stepModel.onEvent(MoviesListInternalRoute.OnLoadNextPage(MovieSection.SectionType.POPULAR))

        // Then
        val state = stepModel.state.filter { it is MoviesListState.Success }.first()
        assertTrue(state is MoviesListState.Success)

        val sections = state.movies
        val updatedSection = sections.first { it.sectionType == MovieSection.SectionType.POPULAR }
        assertEquals(5, updatedSection.movies.size)
    }

    @Test
    fun `WHEN loadNextPage called with error THEN emits Error state`() = runTest(dispatcher) {
        // Given
        val stepModel = MoviesListStepModel(FakeMoviesRepository.createFailureRepository())

        // When
        stepModel.onEvent(MoviesListInternalRoute.OnLoadNextPage(MovieSection.SectionType.TOP_RATED))

        // Then
        val state = stepModel.state.filter { it is MoviesListState.Error }.first()
        assertTrue(state is MoviesListState.Error)
    }
}
