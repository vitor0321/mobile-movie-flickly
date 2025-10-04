package com.walcker.movies.produto.movies.features.ui.features.movieDetails

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
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
import cafe.adriel.voyager.koin.koinScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.walcker.movies.core.step.Step
import com.walcker.movies.core.theme.MoviesAppTheme
import com.walcker.movies.produto.movies.features.ui.components.MovieErrorContent
import com.walcker.movies.produto.movies.features.ui.components.MovieLoadingContent
import com.walcker.movies.produto.movies.features.ui.components.MovieTopAppBar
import com.walcker.movies.produto.movies.features.ui.features.movieDetails.components.ModalBottomSheetDetail
import com.walcker.movies.produto.movies.features.ui.features.movieDetails.components.MovieDetailSuccessContent
import com.walcker.movies.produto.movies.features.ui.preview.mockData.movieTestData
import com.walcker.movies.produto.movies.strings.LocalStrings
import com.walcker.movies.produto.movies.strings.features.MovieDetailString
import com.walcker.movies.produto.movies.strings.features.movieDetailStringsPt
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.ArrowLeft
import org.jetbrains.compose.ui.tooling.preview.Preview

internal class MovieDetailStep(
    private val movieId: Int,
) : Step() {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val strings = LocalStrings.current

        val screenModel = koinScreenModel<MovieDetailsStepModel>()
        val state by screenModel.state.collectAsState()

        LaunchedEffect(movieId) {
            screenModel.onEvent(MovieDetailsInternalRoute.OnMovieDetailsData(movieId = movieId))
        }

        MovieDetailContent(
            state = state,
            string = strings.movieDetailStrings,
            onNavigationBack = { navigator.pop() },
        )
    }

}

@Composable
internal fun MovieDetailContent(
    state: MovieDetailsState,
    string: MovieDetailString,
    onNavigationBack: () -> Unit,
) {
    Scaffold(
        topBar = {
            MovieTopAppBar(
                title = string.title,
                icon = FontAwesomeIcons.Solid.ArrowLeft,
                onNavigationBack = onNavigationBack,
            )
        }
    ) { paddingValues ->
        var youtubeVideoKey by remember { mutableStateOf<String?>(null) }
        var showModal by remember { mutableStateOf(false) }
        if (showModal && youtubeVideoKey != null) {
            ModalBottomSheetDetail(
                url = youtubeVideoKey.orEmpty(),
                onDismissRequest = { showModal = false }
            )
        }

        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            UiStateCheck(
                state = state,
                string = string,
                onWatchClick = {
                    youtubeVideoKey = it
                    showModal = !showModal
                }
            )
        }
    }
}

@Composable
private fun UiStateCheck(
    state: MovieDetailsState,
    string: MovieDetailString,
    onWatchClick: (String) -> Unit,
) {
    when (state) {
        is MovieDetailsState.Loading ->
            MovieLoadingContent()

        is MovieDetailsState.Success ->
            MovieDetailSuccessContent(
                movie = state.movie,
                string = string,
                onWatchClick = { onWatchClick(it) },
            )

        is MovieDetailsState.Error ->
            MovieErrorContent(message = state.message)
    }
}

@Preview
@Composable
private fun LightPreview() {
    MoviesAppTheme(isDarkTheme = false) {
        MovieDetailContent(
            state = MovieDetailsState.Success(movieTestData),
            string = movieDetailStringsPt,
            onNavigationBack = {},
        )
    }
}

@Preview
@Composable
private fun DarkPreview() {
    MoviesAppTheme {
        MovieDetailContent(
            state = MovieDetailsState.Success(movieTestData.copy(moviesTrailerYouTubeKey = null)),
            string = movieDetailStringsPt,
            onNavigationBack = {},
        )
    }
}