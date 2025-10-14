package com.walcker.flickly.products.movies.features.ui.preview.movies

import com.walcker.flickly.products.movies.features.ui.features.home.HomeMoviesState
import com.walcker.flickly.products.movies.features.ui.preview.mockData.movieSectionTestData
import org.jetbrains.compose.ui.tooling.preview.PreviewParameterProvider

internal class MoviesListUiStateProvider : PreviewParameterProvider<HomeMoviesState> {
    override val values: Sequence<HomeMoviesState>
        get() = sequenceOf(
            HomeMoviesState.Success(
                movies = movieSectionTestData,
            ),
            HomeMoviesState.Loading,
            HomeMoviesState.Error(
                message = "Something went wrong. Please try again later."
            )
        )
}