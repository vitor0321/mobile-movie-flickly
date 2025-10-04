package com.walcker.movies.produto.movies.features.ui.features.entrypoint

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.walcker.movies.core.platformImpl
import com.walcker.movies.core.theme.MoviesAppTheme
import com.walcker.movies.produto.movies.features.ui.features.movies.MoviesListScreen
import com.walcker.movies.produto.movies.strings.ProvideStrings
import com.walcker.movies.produto.movies.strings.rememberStrings

public data class MoviesEntrypoint(
    private val isDarkTheme: Boolean,
) : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        val platform = platformImpl()
        val lyricist = rememberStrings(currentLanguageTag = platform.languageSystem)
        ProvideStrings(lyricist) {
            MoviesAppTheme(isDarkTheme = isDarkTheme) {
                navigator.push(MoviesListScreen)
            }
        }
    }
}