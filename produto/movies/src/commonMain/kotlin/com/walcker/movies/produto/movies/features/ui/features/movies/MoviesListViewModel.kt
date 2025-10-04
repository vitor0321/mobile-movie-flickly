package com.walcker.movies.produto.movies.features.ui.features.movies

import cafe.adriel.voyager.core.model.StateScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import com.walcker.movies.produto.movies.features.domain.models.MovieSection
import com.walcker.movies.produto.movies.features.domain.models.MoviesPagination
import com.walcker.movies.produto.movies.features.domain.repository.MoviesRepository
import com.walcker.movies.produto.movies.handle.handleMessageError
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

internal class MoviesListViewModel internal constructor(
    private val moviesRepository: MoviesRepository,
) : StateScreenModel<MoviesListUiState>(MoviesListUiState.Loading) {

    private var currentPagination = MoviesPagination()
    private val loadedSections = mutableMapOf<MovieSection.SectionType, MovieSection>()

    init {
        getMovieSections()
    }

    internal fun onEvent(onEvent: MoviesListInternalRoute) {
        when (onEvent) {
            is MoviesListInternalRoute.OnLoadNextPage -> loadNextPage(sectionType = onEvent.sectionType)
        }
    }

    private fun getMovieSections() {
        screenModelScope.launch {
            moviesRepository.getMoviesSections(currentPagination)
                .onSuccess { movieSections ->
                    updateLoadedSections(movieSections)

                    val orderedSections = MovieSection.SectionType.entries
                        .mapNotNull { loadedSections[it] }

                    mutableState.value = MoviesListUiState.Success(movies = orderedSections.toImmutableList())
                }
                .onFailure { error ->
                    mutableState.value = MoviesListUiState.Error(message = handleMessageError(exception = error))
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

                    mutableState.value = MoviesListUiState.Success(movies = orderedSections.toImmutableList())
                }
                .onFailure { error ->
                    mutableState.value = MoviesListUiState.Error(message = handleMessageError(exception = error))
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