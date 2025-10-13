package com.walcker.movies.produto.movies.features.ui.features.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.walcker.movies.core.step.Step
import com.walcker.movies.core.theme.MoviesAppTheme
import com.walcker.movies.navigator.BatSignalEntry
import com.walcker.movies.navigator.MoviesEntry
import com.walcker.movies.produto.movies.features.domain.models.MovieSection
import com.walcker.movies.produto.movies.features.ui.components.MovieErrorContent
import com.walcker.movies.produto.movies.features.ui.components.MovieTopAppBar
import com.walcker.movies.produto.movies.features.ui.features.home.components.MovieSplashScreen
import com.walcker.movies.produto.movies.features.ui.features.home.components.MoviesListSuccessContent
import com.walcker.movies.produto.movies.features.ui.preview.movies.MoviesListUiStateProvider
import com.walcker.movies.produto.movies.strings.LocalStrings
import com.walcker.movies.produto.movies.strings.features.MoviesListStrings
import com.walcker.movies.produto.movies.strings.features.moviesListStringsPt
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Umbrella
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter
import org.koin.compose.koinInject

internal data object HomeMoviesStep : Step() {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val strings = LocalStrings.current

        val screenModel = koinScreenModel<HomeMoviesStepModel>()
        val state by screenModel.state.collectAsState()

        val onEvent: (HomeMoviesInternalRoute) -> Unit = remember { { screenModel.onEvent(event = it) } }

        val moviesEntry = koinInject<MoviesEntry>()
        val batSignalEntry = koinInject<BatSignalEntry>()

        HomeContent(
            state = state,
            onPosterClick = { movieId ->
                navigator.push(moviesEntry.movieDetails(movieId.toString()))
            },
            onGoBatSignal = {
                navigator.push(batSignalEntry.batSignalEntryPoint())
            },
            strings = strings.moviesListStrings,
            onEvent = onEvent,
        )
    }
}

@Composable
internal fun HomeContent(
    state: HomeMoviesState,
    strings: MoviesListStrings,
    onPosterClick: (movieId: Int) -> Unit,
    onGoBatSignal: () -> Unit,
    onEvent: (HomeMoviesInternalRoute) -> Unit
) {
    Scaffold(
        topBar = {
            MovieTopAppBar(
                title = strings.appName,
                iconBatSignal = FontAwesomeIcons.Solid.Umbrella,
                onBatSignal = onGoBatSignal,
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            UiStateCheck(
                state = state,
                strings = strings,
                onPosterClick = onPosterClick,
                onLoadMore = { onEvent(HomeMoviesInternalRoute.OnLoadNextPage(sectionType = it)) }
            )
        }
    }
}

@Composable
private fun UiStateCheck(
    state: HomeMoviesState,
    strings: MoviesListStrings,
    onPosterClick: (Int) -> Unit,
    onLoadMore: (MovieSection.SectionType) -> Unit,
) {
    when (state) {
        is HomeMoviesState.Loading ->
            MovieSplashScreen()

        is HomeMoviesState.Success ->
            MoviesListSuccessContent(
                strings = strings,
                movies = state.movies,
                onPosterClick = onPosterClick,
                onLoadMore = onLoadMore
            )

        is HomeMoviesState.Error ->
            MovieErrorContent(message = state.message)
    }
}

@Preview
@Composable()
private fun Preview(
    @PreviewParameter(MoviesListUiStateProvider::class) state: HomeMoviesState,
) {
    MoviesAppTheme {
        HomeContent(
            state = state,
            strings = moviesListStringsPt,
            onPosterClick = { },
            onGoBatSignal = { },
            onEvent = {},
        )
    }
}