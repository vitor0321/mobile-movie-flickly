package com.walcker.flickly.products.movies.features.ui.features.movieDetails

import cafe.adriel.voyager.core.model.screenModelScope
import com.walcker.flickly.core.navigation.NavigatorHolder
import com.walcker.flickly.core.ui.stepModel.StepModel
import com.walcker.flickly.products.movies.features.domain.repository.MoviesRepository
import com.walcker.flickly.products.movies.handle.handleMessageError
import com.walcker.flickly.products.movies.strings.MoviesStringsHolder
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class MovieDetailsStepModel internal constructor(
    private val moviesRepository: MoviesRepository,
    private val stringsHolder: MoviesStringsHolder,
    private val navigatorHolder: NavigatorHolder,
) : StepModel<MovieDetailsState, MovieDetailsInternalRoute>(initialState = MovieDetailsState()) {

    private val eventChannel = Channel<MovieDetailsInternalEvents>()
    val events = eventChannel.receiveAsFlow()

    override fun onEvent(event: MovieDetailsInternalRoute) {
        when (event) {
            is MovieDetailsInternalRoute.OnMovieDetailsData -> getMovieDetails(movieId = event.movieId)
            is MovieDetailsInternalRoute.OnPopBackStack -> navigatorHolder.navigator.pop()
            is MovieDetailsInternalRoute.OnRetry -> onRetry()
        }
    }

    private fun onRetry() {
        mutableState.update { currentData -> currentData.copy(loading = true) }
        getMovieDetails(movieId = state.value.movie?.id ?: 0)
    }

    private fun getMovieDetails(movieId: Int) {
        screenModelScope.launch {
            moviesRepository.getMovieDetail(movieId = movieId)
                .onSuccess { movie ->
                    mutableState.update { currentData ->
                        currentData.copy(
                            string = stringsHolder.strings.movieDetailStrings,
                            movie = movie,
                            loading = false,
                        )
                    }
                }
                .onFailure { error ->
                    mutableState.update { currentData ->
                        currentData.copy(
                            string = stringsHolder.strings.movieDetailStrings,
                            loading = false,
                        )
                    }
                    eventChannel.trySend(MovieDetailsInternalEvents.OnError(errorMessage = handleMessageError(exception = error)))
                }
        }
    }
}