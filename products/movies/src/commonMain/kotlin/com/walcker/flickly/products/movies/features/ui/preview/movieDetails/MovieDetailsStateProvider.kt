package com.walcker.flickly.products.movies.features.ui.preview.movieDetails

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.walcker.flickly.products.movies.features.ui.features.movieDetails.MovieDetailsState
import com.walcker.flickly.products.movies.features.ui.preview.mockData.movieTestData
import com.walcker.flickly.products.movies.strings.MovieDetailStrings

internal class MovieDetailsStateProvider : PreviewParameterProvider<MovieDetailsState> {
    override val values: Sequence<MovieDetailsState>
        get() = sequenceOf(
            MovieDetailsState(
                loading = false,
                strings = MovieDetailStrings(
                    title = "Detalhes",
                    buttonText = "Assistir trailer",
                ),
                movie = movieTestData,
            ),
            MovieDetailsState(
                loading = false,
                strings = MovieDetailStrings(
                    title = "Detalhes",
                    buttonText = "Assistir trailer",
                ),
                movie = movieTestData.copy(moviesTrailerYouTubeKey = null),
            ),
            MovieDetailsState(loading = true),
        )
}