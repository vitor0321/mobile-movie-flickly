package com.walcker.flickly.products.movies.features.ui.preview.home

import com.walcker.flickly.products.movies.features.ui.features.home.HomeMoviesState
import com.walcker.flickly.products.movies.features.ui.preview.mockData.movieSectionTestData
import com.walcker.flickly.products.movies.strings.features.moviesListStringsPt
import org.jetbrains.compose.ui.tooling.preview.PreviewParameterProvider

internal class HomeMoviesStateProvider : PreviewParameterProvider<HomeMoviesState> {
    override val values: Sequence<HomeMoviesState>
        get() = sequenceOf(
            HomeMoviesState(
                loading = false,
                string = moviesListStringsPt,
                movies = movieSectionTestData,
            ),
            HomeMoviesState(loading = true),
            HomeMoviesState(errorMessage = "Something went wrong. Please try again later.")
        )
}