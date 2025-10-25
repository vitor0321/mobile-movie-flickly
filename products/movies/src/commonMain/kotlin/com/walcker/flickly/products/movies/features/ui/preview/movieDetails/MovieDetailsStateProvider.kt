package com.walcker.flickly.products.movies.features.ui.preview.movieDetails

import com.walcker.flickly.products.movies.features.ui.features.movieDetails.MovieDetailsState
import com.walcker.flickly.products.movies.features.ui.preview.mockData.movieTestData
import com.walcker.flickly.products.movies.strings.MovieDetailStrings
import org.jetbrains.compose.ui.tooling.preview.PreviewParameterProvider

internal class MovieDetailsStateProvider : PreviewParameterProvider<MovieDetailsState> {
    override val values: Sequence<MovieDetailsState>
        get() = sequenceOf(
            MovieDetailsState(
                loading = false,
                string = MovieDetailStrings(
                    title = "Detalhes",
                    buttonText = "Assistir trailer",
                ),
                movie = movieTestData,
            ),
            MovieDetailsState(
                loading = false,
                string = MovieDetailStrings(
                    title = "Detalhes",
                    buttonText = "Assistir trailer",
                ),
                movie = movieTestData.copy(moviesTrailerYouTubeKey = null),
            ),
            MovieDetailsState(loading = true),
        )
}