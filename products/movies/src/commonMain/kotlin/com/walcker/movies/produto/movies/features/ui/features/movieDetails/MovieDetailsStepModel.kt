package com.walcker.movies.produto.movies.features.ui.features.movieDetails

import cafe.adriel.voyager.core.model.screenModelScope
import com.walcker.movies.core.stepModel.StepModel
import com.walcker.movies.produto.movies.features.domain.repository.MoviesRepository
import com.walcker.movies.produto.movies.handle.handleMessageError
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

internal class MovieDetailsStepModel internal constructor(
    private val moviesRepository: MoviesRepository,
) : StepModel<MovieDetailsState, MovieDetailsInternalRoute>(MovieDetailsState.Loading) {

    override fun onEvent(event: MovieDetailsInternalRoute) {
        when (event) {
            is MovieDetailsInternalRoute.OnMovieDetailsData -> getMovieDetails(movieId = event.movieId)
        }
    }

    private fun getMovieDetails(movieId: Int) {
        screenModelScope.launch {
            moviesRepository.getMovieDetail(movieId = movieId)
                .onSuccess { movie ->
                    mutableState.update { MovieDetailsState.Success(movie) }
                }
                .onFailure { error ->
                    mutableState.update { MovieDetailsState.Error(handleMessageError(exception = error)) }
                }
        }
    }
}