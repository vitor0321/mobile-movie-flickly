package com.walcker.movies.produto.movies.features.ui.features.home

import com.walcker.movies.produto.movies.features.domain.models.MovieSection

internal interface HomeMoviesInternalRoute {
    data class OnLoadNextPage(val sectionType: MovieSection.SectionType) : HomeMoviesInternalRoute
    data class OnGoMovieDetails(val movieId: Int) : HomeMoviesInternalRoute
    data object OnGoToBatSignal : HomeMoviesInternalRoute
}