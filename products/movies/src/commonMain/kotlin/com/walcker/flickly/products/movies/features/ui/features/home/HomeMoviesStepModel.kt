package com.walcker.flickly.products.movies.features.ui.features.home

import cafe.adriel.voyager.core.model.screenModelScope
import com.walcker.flickly.core.domain.setting.PasswordSettings
import com.walcker.flickly.core.domain.setting.model.PasswordSettingsHolder.REQUIRED_PASSWORD
import com.walcker.flickly.core.navigation.NavigatorHolder
import com.walcker.flickly.core.ui.stepModel.StepModel
import com.walcker.flickly.navigator.AudioDestination
import com.walcker.flickly.navigator.MoviesDestination
import com.walcker.flickly.products.movies.features.domain.models.MovieSection
import com.walcker.flickly.products.movies.features.domain.models.MoviesPagination
import com.walcker.flickly.products.movies.features.domain.repository.MoviesRepository
import com.walcker.flickly.products.movies.handle.handleMessageError
import com.walcker.flickly.products.movies.strings.MoviesStringsHolder
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class HomeMoviesStepModel internal constructor(
    private val moviesRepository: MoviesRepository,
    private val navigatorHolder: NavigatorHolder,
    private val stringsHolder: MoviesStringsHolder,
    private val moviesDestination: MoviesDestination,
    private val audioDestination: AudioDestination,
    private val passwordSettings: PasswordSettings,
) : StepModel<HomeMoviesState, HomeMoviesInternalRoute>(HomeMoviesState()) {

    private val eventChannel = Channel<HomeMoviesInternalEvents>()
    val events = eventChannel.receiveAsFlow()

    private var currentPagination = MoviesPagination()
    private val loadedSections = mutableMapOf<MovieSection.SectionType, MovieSection>()

    init {
        getMovieSections()
    }

    override fun onEvent(event: HomeMoviesInternalRoute) {
        when (event) {
            is HomeMoviesInternalRoute.OnLoadNextPage -> loadNextPage(sectionType = event.sectionType)
            is HomeMoviesInternalRoute.OnGoToAudio -> validateAndNavigate(event.password)
            is HomeMoviesInternalRoute.OnChangePassword -> changePassword(event.newPassword)
            is HomeMoviesInternalRoute.OnGoMovieDetails -> {
                navigatorHolder.navigator.push(moviesDestination.movieDetails(event.movieId.toString()))
            }

            is HomeMoviesInternalRoute.OnRetry -> onRetry()
        }
    }

    private fun validateAndNavigate(enteredPassword: String) {
        val validPassword = passwordSettings.getSavedPassword() ?: REQUIRED_PASSWORD
        if (enteredPassword == validPassword) {
            if (!passwordSettings.hasCustomPassword()) {
                eventChannel.trySend(HomeMoviesInternalEvents.OnShowChangePassword)
            } else {
                navigatorHolder.navigator.push(audioDestination.audioEntryPoint())
            }
        } else {
            eventChannel.trySend(
                HomeMoviesInternalEvents.OnError(
                    errorMessage = stringsHolder.strings.moviesListStrings.invalidPasswordMessage
                )
            )
        }
    }

    private fun changePassword(newPassword: String) {
        passwordSettings.savePassword(newPassword)
        navigatorHolder.navigator.push(audioDestination.audioEntryPoint())
    }

    private fun onRetry() {
        mutableState.update { currentData -> currentData.copy(loading = true) }
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
                            loading = false,
                        )
                    }
                    eventChannel.trySend(HomeMoviesInternalEvents.OnError(errorMessage = handleMessageError(exception = error)))
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
                            loading = false,
                        )
                    }
                    eventChannel.trySend(HomeMoviesInternalEvents.OnError(errorMessage = handleMessageError(exception = error)))
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