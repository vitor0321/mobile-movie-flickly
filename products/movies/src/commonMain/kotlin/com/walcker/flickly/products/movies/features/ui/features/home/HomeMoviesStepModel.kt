package com.walcker.flickly.products.movies.features.ui.features.home

import cafe.adriel.voyager.core.model.screenModelScope
import com.walcker.flickly.core.ui.stepModel.StepModel
import com.walcker.flickly.navigator.AudioEntry
import com.walcker.flickly.navigator.MoviesEntry
import com.walcker.flickly.products.movies.features.domain.models.MovieSection
import com.walcker.flickly.products.movies.features.domain.models.MoviesPagination
import com.walcker.flickly.products.movies.features.domain.repository.MoviesRepository
import com.walcker.flickly.products.movies.handle.handleMessageError
import com.walcker.flickly.products.movies.strings.StringsHolder
import com.walcker.movies.core.navigation.NavigatorHolder
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class HomeMoviesStepModel internal constructor(
    private val moviesRepository: MoviesRepository,
    private val navigatorHolder: NavigatorHolder,
    private val stringsHolder: StringsHolder,
    private val moviesEntry: MoviesEntry,
    private val audioEntry: AudioEntry,
) : StepModel<HomeMoviesState, HomeMoviesInternalRoute>(HomeMoviesState()) {

    private var currentPagination = MoviesPagination()
    private val loadedSections = mutableMapOf<MovieSection.SectionType, MovieSection>()

    init {
        getMovieSections()
    }

    override fun onEvent(event: HomeMoviesInternalRoute) {
        when (event) {
            is HomeMoviesInternalRoute.OnLoadNextPage -> loadNextPage(sectionType = event.sectionType)
            is HomeMoviesInternalRoute.OnGoToAudio -> {
                navigatorHolder.navigator.push(audioEntry.audioEntryPoint())
            }
            is HomeMoviesInternalRoute.OnGoMovieDetails -> {
                navigatorHolder.navigator.push(moviesEntry.movieDetails(event.movieId.toString()))
            }
            is HomeMoviesInternalRoute.OnRetry -> onRetry()
        }
    }

    private fun onRetry() {
        mutableState.update { currentData ->
            currentData.copy(
                errorMessage = null,
                loading = true,
            )
        }
        getMovieSections()
    }

    private fun getMovieSections() {
        screenModelScope.launch {
            moviesRepository.getMoviesSections(currentPagination)
                .onSuccess { movieSections ->
                    updateLoadedSections(movieSections)

                    val orderedSections = MovieSection.SectionType.entries
                        .mapNotNull { loadedSections[it] }

                    mutableState.update { currentData ->
                        currentData.copy(
                            string = stringsHolder.strings.moviesListStrings,
                            movies = orderedSections.toImmutableList(),
                            loading = false,
                        )
                    }
                }
                .onFailure { error ->
                    mutableState.update { currentData ->
                        currentData.copy(
                            string = stringsHolder.strings.moviesListStrings,
                            errorMessage = handleMessageError(exception = error),
                            loading = false,
                        )
                    }
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
                    mutableState.update { currentData ->
                        currentData.copy(
                            string = stringsHolder.strings.moviesListStrings,
                            movies = orderedSections.toImmutableList(),
                            loading = false,
                        )
                    }
                }
                .onFailure { error ->
                    mutableState.update { currentData ->
                        currentData.copy(
                            string = stringsHolder.strings.moviesListStrings,
                            errorMessage = handleMessageError(exception = error),
                            loading = false,
                        )
                    }
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