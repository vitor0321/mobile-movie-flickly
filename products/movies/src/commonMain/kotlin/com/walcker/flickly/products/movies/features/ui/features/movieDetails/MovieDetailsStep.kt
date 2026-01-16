package com.walcker.flickly.products.movies.features.ui.features.movieDetails

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import cafe.adriel.voyager.koin.koinScreenModel
import com.walcker.flickly.cedarDS.CedarLoadingContent
import com.walcker.flickly.cedarDS.CedarTopAppBar
import com.walcker.flickly.core.ui.step.Step
import com.walcker.flickly.core.ui.theme.MoviesAppTheme
import com.walcker.flickly.core.utils.media.OpenVideo
import com.walcker.flickly.products.movies.features.ui.features.movieDetails.components.MovieDetailSuccessContent
import com.walcker.flickly.products.movies.features.ui.preview.movieDetails.MovieDetailsStateProvider
import com.walcker.flickly.products.movies.strings.MovieDetailStrings
import com.walcker.flickly.products.movies.strings.movieDetailStringsPt
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.ArrowLeft
import kotlin.reflect.KClass

internal class MovieDetailStep(
    private val movieId: Int,
) : Step() {

    @Composable
    override fun Content() {
        val model = koinScreenModel<MovieDetailsStepModel>()
        val state by model.state.collectAsState()

        LaunchedEffect(movieId) {
            model.onEvent(MovieDetailsInternalRoute.OnMovieDetailsData(movieId = movieId))
        }

        MovieDetailsStepEvents(
            model = model,
            onEvent = model::onEvent,
            content = {
                MovieDetailContent(
                    state = state,
                    string = state.strings,
                    onEvent = model::onEvent,
                )
            }
        )
    }
}

@Composable
internal fun MovieDetailContent(
    state: MovieDetailsState,
    string: MovieDetailStrings,
    onEvent: (MovieDetailsInternalRoute) -> Unit,
) {
    Scaffold(
        topBar = {
            CedarTopAppBar(
                title = string.title,
                icon = FontAwesomeIcons.Solid.ArrowLeft,
                onNavigationBack = { onEvent(MovieDetailsInternalRoute.OnPopBackStack) },
            )
        }
    ) { paddingValues ->
        var videoUrlToOpen by remember { mutableStateOf<String?>(null) }

        videoUrlToOpen?.let { url ->
            OpenVideo(url = url)
            LaunchedEffect(Unit) {
                videoUrlToOpen = null
            }
        }

        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center,
        ) {
            state.movie?.let {
                MovieDetailSuccessContent(
                    movie = state.movie,
                    string = string,
                    onWatchClick = { videoUrl ->
                        videoUrlToOpen = videoUrl
                    },
                )
            }

            if (state.loading)
                CedarLoadingContent()
        }
    }
}

@Preview
@Composable()
private fun DarkPreview(
    @PreviewParameter(MovieDetailsStateProvider::class) state: MovieDetailsState,
) {
    MoviesAppTheme(isDarkTheme = true) {
        MovieDetailContent(
            state = state,
            string = movieDetailStringsPt,
            onEvent = {},
        )
    }
}

@Preview
@Composable()
private fun LightPreview(
    @PreviewParameter(provider = MovieDetailsStateProvider::class) state: MovieDetailsState,
) {
    MoviesAppTheme(isDarkTheme = false) {
        MovieDetailContent(
            state = state,
            string = movieDetailStringsPt,
            onEvent = {},
        )
    }
}