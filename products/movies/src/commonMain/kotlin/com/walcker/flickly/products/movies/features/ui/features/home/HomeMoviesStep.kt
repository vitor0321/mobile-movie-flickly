package com.walcker.flickly.products.movies.features.ui.features.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import cafe.adriel.voyager.koin.koinScreenModel
import com.walcker.flickly.cedarDS.CedarTopAppBar
import com.walcker.flickly.core.ui.step.Step
import com.walcker.flickly.core.ui.theme.MoviesAppTheme
import com.walcker.flickly.products.movies.features.ui.features.home.components.AlertPassword
import com.walcker.flickly.products.movies.features.ui.features.home.components.MovieSplashScreen
import com.walcker.flickly.products.movies.features.ui.features.home.components.MoviesListSuccessContent
import com.walcker.flickly.products.movies.features.ui.preview.home.HomeMoviesStateProvider
import com.walcker.flickly.products.movies.strings.MoviesListStrings
import com.walcker.flickly.products.movies.strings.moviesListStringsPt
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Tree

internal data object HomeMoviesStep : Step() {

    @Composable
    override fun Content() {
        val model = koinScreenModel<HomeMoviesStepModel>()
        val state by model.state.collectAsState()

        HomeMoviesStepEvents(
            model = model,
            onEvent = model::onEvent,
            content = {
                HomeContent(
                    state = state,
                    strings = state.string,
                    onEvent = model::onEvent,
                )
            }
        )
    }
}

@Composable
internal fun HomeContent(
    state: HomeMoviesState,
    strings: MoviesListStrings,
    onEvent: (HomeMoviesInternalRoute) -> Unit
) {
    var showPasswordDialog by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            CedarTopAppBar(
                title = strings.appName,
                iconAudio = FontAwesomeIcons.Solid.Tree,
                onAudio = { showPasswordDialog = true },
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

            if (state.loading)
                MovieSplashScreen()

            if (showPasswordDialog)
                AlertPassword(
                    strings = strings,
                    showPasswordDialog = { showPasswordDialog = it },
                    onEvent = onEvent
                )
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