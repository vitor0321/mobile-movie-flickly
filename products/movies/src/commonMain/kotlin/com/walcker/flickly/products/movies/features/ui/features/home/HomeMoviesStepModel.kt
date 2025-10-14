package com.walcker.flickly.products.movies.features.ui.features.home

import cafe.adriel.voyager.core.model.screenModelScope
import com.walcker.flickly.core.stepModel.StepModel
import com.walcker.flickly.products.movies.features.domain.models.MovieSection
import com.walcker.flickly.products.movies.features.domain.models.MoviesPagination
import com.walcker.flickly.products.movies.features.domain.repository.MoviesRepository
import com.walcker.flickly.products.movies.handle.handleMessageError
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class HomeMoviesStepModel internal constructor(
    private val moviesRepository: MoviesRepository,
) : StepModel<HomeMoviesState, HomeMoviesInternalRoute>(HomeMoviesState.Loading) {

    private var currentPagination = MoviesPagination()
    private val loadedSections = mutableMapOf<MovieSection.SectionType, MovieSection>()

    init {
        getMovieSections()
    }

    override fun onEvent(event: HomeMoviesInternalRoute) {
        when (event) {
            is HomeMoviesInternalRoute.OnLoadNextPage -> loadNextPage(sectionType = event.sectionType)
            is HomeMoviesInternalRoute.OnGoToBatSignal -> {}
            is HomeMoviesInternalRoute.OnGoMovieDetails -> {

            }
        }
    }

    private fun getMovieSections() {
        screenModelScope.launch {
            moviesRepository.getMoviesSections(currentPagination)
                .onSuccess { movieSections ->
                    updateLoadedSections(movieSections)

                    val orderedSections = MovieSection.SectionType.entries
                        .mapNotNull { loadedSections[it] }

                    mutableState.update { HomeMoviesState.Success(movies = orderedSections.toImmutableList()) }
                }
                .onFailure { error ->
                    mutableState.update { HomeMoviesState.Error(message = handleMessageError(exception = error)) }
                }
        }
    }

    private fun loadNextPage(sectionType: MovieSection.SectionType) {
        currentPagination = currentPagination.increment(sectionType)
        screenModelScope.launch {
            moviesRepository.getMovieSection(sectionType, currentPagination.pageFor(sectionType))
                .onSuccess { newSection ->
                    updateLoadedSections(listOf(newSection))

                    val orderedSections = MovieSection.SectionType.entries
                        .mapNotNull { loadedSections[it] }

                    mutableState.update { HomeMoviesState.Success(movies = orderedSections.toImmutableList()) }
                }
                .onFailure { error ->
                    mutableState.update { HomeMoviesState.Error(message = handleMessageError(exception = error)) }
                }
        }
    }

    private fun updateLoadedSections(newSections: List<MovieSection>) {
        newSections.forEach { section ->
            val current = loadedSections[section.sectionType]
            loadedSections[section.sectionType] = section.copy(
                movies = if (current != null)
                    (current.movies + section.movies).toImmutableList()
                else section.movies
            )
        }
    }
}