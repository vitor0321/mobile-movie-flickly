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
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.walcker.movies.core.step.Step
import com.walcker.movies.core.theme.MoviesAppTheme
import com.walcker.movies.produto.movies.features.domain.models.MovieSection
import com.walcker.movies.produto.movies.features.ui.components.MovieErrorContent
import com.walcker.movies.produto.movies.features.ui.components.MovieTopAppBar
import com.walcker.movies.produto.movies.features.ui.features.movieDetails.MovieDetailStep
import com.walcker.movies.produto.movies.features.ui.features.movies.components.MovieSplashScreen
import com.walcker.movies.produto.movies.features.ui.features.movies.components.MoviesListSuccessContent
import com.walcker.movies.produto.movies.features.ui.preview.movies.MoviesListUiStateProvider
import com.walcker.movies.produto.movies.strings.LocalStrings
import com.walcker.movies.produto.movies.strings.features.MoviesListStrings
import com.walcker.movies.produto.movies.strings.features.moviesListStringsPt
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter

internal data object MoviesListStep : Step() {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val strings = LocalStrings.current

        val screenModel = koinScreenModel<MoviesListStepModel>()
        val state by screenModel.state.collectAsState()

        val onEvent: (MoviesListInternalRoute) -> Unit = remember { { screenModel.onEvent(event = it) } }

        MoviesListContent(
            state = state,
            onPosterClick = { movieId ->
                navigator.push(MovieDetailStep(movieId))
            },
            strings = strings.moviesListStrings,
            onEvent = onEvent,
        )
    }
}

@Composable
internal fun MoviesListContent(
    state: MoviesListState,
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
                state = state,
                strings = strings,
                onPosterClick = onPosterClick,
                onLoadMore = { onEvent(MoviesListInternalRoute.OnLoadNextPage(sectionType = it)) }
            )
        }
    }
}

@Composable
private fun UiStateCheck(
    state: MoviesListState,
    strings: MoviesListStrings,
    onPosterClick: (Int) -> Unit,
    onLoadMore: (MovieSection.SectionType) -> Unit,
) {
    when (state) {
        is MoviesListState.Loading ->
            MovieSplashScreen()

        is MoviesListState.Success ->
            MoviesListSuccessContent(
                strings = strings,
                movies = state.movies,
                onPosterClick = onPosterClick,
                onLoadMore = onLoadMore
            )

        is MoviesListState.Error ->
            MovieErrorContent(message = state.message)
    }
}

@Preview
@Composable()
private fun Preview(
    @PreviewParameter(MoviesListUiStateProvider::class) state: MoviesListState,
) {
    MoviesAppTheme {
        MoviesListContent(
            state = state,
            strings = moviesListStringsPt,
            onPosterClick = { },
            onEvent = {},
        )
    }
}