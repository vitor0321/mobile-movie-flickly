package com.walcker.flickly.products.movies.features.ui.features.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.koin.koinScreenModel
import com.walcker.flickly.cedarDS.CedarErrorContent
import com.walcker.flickly.cedarDS.CedarTopAppBar
import com.walcker.flickly.core.ui.step.Step
import com.walcker.flickly.core.ui.theme.MoviesAppTheme
import com.walcker.flickly.products.movies.features.ui.features.home.components.MovieSplashScreen
import com.walcker.flickly.products.movies.features.ui.features.home.components.MoviesListSuccessContent
import com.walcker.flickly.products.movies.features.ui.preview.home.HomeMoviesStateProvider
import com.walcker.flickly.products.movies.strings.features.MoviesListStrings
import com.walcker.flickly.products.movies.strings.features.moviesListStringsPt
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Tree
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter

internal data object HomeMoviesStep : Step() {

    @Composable
    override fun Content() {
        val model = koinScreenModel<HomeMoviesStepModel>()
        val state by model.state.collectAsState()

        HomeContent(
            state = state,
            strings = state.string,
            onEvent = model::onEvent,
        )
    }
}

@Composable
internal fun HomeContent(
    state: HomeMoviesState,
    strings: MoviesListStrings,
    onEvent: (HomeMoviesInternalRoute) -> Unit
) {
    Scaffold(
        topBar = {
            CedarTopAppBar(
                title = strings.appName,
                iconAudio = FontAwesomeIcons.Solid.Tree,
                onAudio = { onEvent(HomeMoviesInternalRoute.OnGoToAudio) },
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            state.movies?.let {
                MoviesListSuccessContent(
                    strings = strings,
                    movies = state.movies,
                    onPosterClick = { onEvent(HomeMoviesInternalRoute.OnGoMovieDetails(movieId = it)) },
                    onLoadMore = { onEvent(HomeMoviesInternalRoute.OnLoadNextPage(sectionType = it)) },
                )
            }

            state.errorMessage?.let {
                CedarErrorContent(
                    message = state.errorMessage,
                    onRetry = { onEvent(HomeMoviesInternalRoute.OnRetry) }
                )
            }

            if (state.loading)
                MovieSplashScreen()
        }
    }
}

@Preview
@Composable()
private fun Preview(
    @PreviewParameter(HomeMoviesStateProvider::class) state: HomeMoviesState,
) {
    MoviesAppTheme {
        HomeContent(
            state = state,
            strings = moviesListStringsPt,
            onEvent = {},
        )
    }
}