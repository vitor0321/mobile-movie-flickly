package com.walcker.movies.produto.movies.features.ui.features.movies

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.walcker.movies.core.theme.MoviesAppTheme
import com.walcker.movies.produto.movies.features.domain.models.MovieSection
import com.walcker.movies.produto.movies.features.ui.components.MovieErrorContent
import com.walcker.movies.produto.movies.features.ui.components.MovieTopAppBar
import com.walcker.movies.produto.movies.features.ui.features.movieDetails.MovieDetailScreen
import com.walcker.movies.produto.movies.features.ui.features.movies.components.MovieSplashScreen
import com.walcker.movies.produto.movies.features.ui.features.movies.components.MoviesListSuccessContent
import com.walcker.movies.produto.movies.features.ui.preview.movies.MoviesListUiStateProvider
import com.walcker.movies.produto.movies.strings.LocalStrings
import com.walcker.movies.produto.movies.strings.features.MoviesListStrings
import com.walcker.movies.produto.movies.strings.features.moviesListStringsPt
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter

internal data object MoviesListScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val strings = LocalStrings.current

        val screenModel = koinScreenModel<MoviesListViewModel>()
        val state by screenModel.state.collectAsState()

        val onEvent: (MoviesListInternalRoute) -> Unit = remember { { screenModel.onEvent(onEvent = it) } }

        MoviesListScreenContent(
            uiState = state,
            onPosterClick = { movieId ->
                navigator.push(MovieDetailScreen(movieId))
            },
            strings = strings.moviesListStrings,
            onEvent = onEvent,
        )
    }
}

@Composable
private fun MoviesListScreenContent(
    uiState: MoviesListUiState,
    strings: MoviesListStrings,
    onPosterClick: (movieId: Int) -> Unit,
    onEvent: (MoviesListInternalRoute) -> Unit
) {
    Scaffold(
        topBar = { MovieTopAppBar(title = strings.appName) }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            UiStateCheck(
                uiState = uiState,
                strings = strings,
                onPosterClick = onPosterClick,
                onLoadMore = { onEvent(MoviesListInternalRoute.OnLoadNextPage(sectionType = it)) }
            )
        }
    }
}

@Composable
private fun UiStateCheck(
    uiState: MoviesListUiState,
    strings: MoviesListStrings,
    onPosterClick: (Int) -> Unit,
    onLoadMore: (MovieSection.SectionType) -> Unit,
) {
    when (uiState) {
        is MoviesListUiState.Loading ->
            MovieSplashScreen()

        is MoviesListUiState.Success ->
            MoviesListSuccessContent(
                strings = strings,
                movies = uiState.movies,
                onPosterClick = onPosterClick,
                onLoadMore = onLoadMore
            )

        is MoviesListUiState.Error ->
            MovieErrorContent(message = uiState.message)
    }
}

@Preview
@Composable()
private fun Preview(
    @PreviewParameter(MoviesListUiStateProvider::class) uiState: MoviesListUiState,
) {
    MoviesAppTheme {
        MoviesListScreenContent(
            uiState = uiState,
            strings = moviesListStringsPt,
            onPosterClick = { },
            onEvent = {},
        )
    }
}