package com.walcker.movies.produto.movies.features.ui.preview.movies

import com.walcker.movies.produto.movies.features.ui.features.movies.MoviesListState
import com.walcker.movies.produto.movies.features.ui.preview.mockData.movieSectionTestData
import org.jetbrains.compose.ui.tooling.preview.PreviewParameterProvider

internal class MoviesListUiStateProvider : PreviewParameterProvider<MoviesListState> {
    override val values: Sequence<MoviesListState>
        get() = sequenceOf(
            MoviesListState.Success(
                movies = movieSectionTestData,
            ),
            MoviesListState.Loading,
            MoviesListState.Error(
                message = "Something went wrong. Please try again later."
            )
        )
}