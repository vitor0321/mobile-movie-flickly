package com.walcker.flickly.products.movies.features.ui.preview.home

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.walcker.flickly.products.movies.features.ui.features.home.HomeMoviesState
import com.walcker.flickly.products.movies.features.ui.preview.mockData.movieSectionTestData
import com.walcker.flickly.products.movies.strings.moviesListStringsPt

internal class HomeMoviesStateProvider : PreviewParameterProvider<HomeMoviesState> {
    override val values: Sequence<HomeMoviesState>
        get() = sequenceOf(
            HomeMoviesState(
                loading = false,
                string = moviesListStringsPt,
                movies = movieSectionTestData,
            ),
            HomeMoviesState(loading = true),
        )
}