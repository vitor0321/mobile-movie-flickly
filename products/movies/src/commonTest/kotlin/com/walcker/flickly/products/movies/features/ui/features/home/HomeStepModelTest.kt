package com.walcker.flickly.products.movies.features.ui.features.home

import com.walcker.flickly.core.navigation.NavigatorHolder
import com.walcker.flickly.navigator.fakeNavigation.FakeAudioEntry
import com.walcker.flickly.navigator.fakeNavigation.FakeMoviesEntry
import com.walcker.flickly.products.movies.features.domain.models.MovieSection
import com.walcker.flickly.products.movies.features.domain.repository.MoviesRepository
import com.walcker.flickly.products.movies.mockFakes.FakeMoviesRepository
import com.walcker.flickly.products.movies.strings.EnMoviesStrings
import com.walcker.flickly.products.movies.strings.MoviesStringsHolder
import com.walcker.flickly.products.movies.utils.CoroutineMainDispatcherTestRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

@ExperimentalCoroutinesApi
internal class HomeStepModelTest : CoroutineMainDispatcherTestRule() {

    private fun createViewModel(
        moviesRepository: MoviesRepository
    ): HomeMoviesStepModel {
        val stringsHolder = MoviesStringsHolder().apply { setStrings(EnMoviesStrings) }
        return HomeMoviesStepModel(
            moviesRepository,
            navigatorHolder = NavigatorHolder(),
            stringsHolder = stringsHolder,
            moviesEntry = FakeMoviesEntry(),
            audioEntry = FakeAudioEntry(),
        )
    }

    @Test
    fun `GIVEN a successful list fetch WHEN init THEN uiState should be Loading`() = runTest(dispatcher) {
        val stepModel = createViewModel(FakeMoviesRepository.createSuccessRepository())
        val initialState = stepModel.state.first()
        assertTrue(initialState.loading)
    }

    @Test
    fun `GIVEN a successful list fetch WHEN init completes THEN uiState should be Success`() = runTest(dispatcher) {
        val stepModel = createViewModel(FakeMoviesRepository.createSuccessRepository())

        runCurrent()
        val successState = stepModel.state.filter { !it.loading && it.movies != null }.first()
        assertEquals(5, successState.movies?.first()?.movies?.size)
    }

    @Test
    fun `GIVEN next page load WHEN loadNextPage is called THEN movies list accumulates`() = runTest(dispatcher) {
        val stepModel = createViewModel(FakeMoviesRepository.createSuccessRepository())
        runCurrent()
        val initialSuccess = stepModel.state.filter { !it.loading && it.movies != null }.first()
        val popularInitial = initialSuccess.movies!!.first { it.sectionType == MovieSection.SectionType.POPULAR }
        val initialCount = popularInitial.movies.size

        stepModel.onEvent(HomeMoviesInternalRoute.OnLoadNextPage(MovieSection.SectionType.POPULAR))
        runCurrent()
        val nextSuccess = stepModel.state.filter { !it.loading && it.movies != null }.first()
        val popularAfter = nextSuccess.movies!!.first { it.sectionType == MovieSection.SectionType.POPULAR }
        assertEquals(initialCount + 5, popularAfter.movies.size) // acumula +5
    }

    @Test
    fun `GIVEN a failed movies list fetch WHEN init THEN uiState should be Error`() = runTest(dispatcher) {
        val errorMessage = "Algo deu errado. Tente novamente mais tarde."
        val moviesRepository = FakeMoviesRepository.createFailureRepository(RuntimeException("Repository error"))
        val stepModel = createViewModel(moviesRepository = moviesRepository)
        runCurrent()
        val errorState = stepModel.state.filter { !it.loading && it.errorMessage != null }.first()
        assertEquals(errorMessage, errorState.errorMessage)
    }

    @Test
    fun `WHEN loadNextPage called THEN only that section is updated and Success state is emitted`() = runTest(dispatcher) {
        val stepModel = createViewModel(FakeMoviesRepository.createSuccessRepository())
        runCurrent()
        val initialSuccess = stepModel.state.filter { !it.loading && it.movies != null }.first()
        val popularInitialSize = initialSuccess.movies!!.first { it.sectionType == MovieSection.SectionType.POPULAR }.movies.size

        stepModel.onEvent(HomeMoviesInternalRoute.OnLoadNextPage(MovieSection.SectionType.POPULAR))
        runCurrent()
        val afterLoad = stepModel.state.filter { !it.loading && it.movies != null }.first()
        val updatedPopularSize = afterLoad.movies!!.first { it.sectionType == MovieSection.SectionType.POPULAR }.movies.size

        assertEquals(popularInitialSize + 5, updatedPopularSize)
    }

    @Test
    fun `WHEN loadNextPage called with error THEN emits Error state`() = runTest(dispatcher) {
        val errorMessage = "Algo deu errado. Tente novamente mais tarde."
        val stepModel = createViewModel(FakeMoviesRepository.createFailureRepository())
        runCurrent()

        stepModel.onEvent(HomeMoviesInternalRoute.OnLoadNextPage(MovieSection.SectionType.TOP_RATED))
        runCurrent()
        val errorState = stepModel.state.filter { !it.loading && it.errorMessage != null }.first()
        assertEquals(errorMessage, errorState.errorMessage)
    }
}
