package com.walcker.flickly.products.movies.features.ui.features.entrypoint

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.walcker.flickly.core.platformImpl
import com.walcker.flickly.core.ui.theme.MoviesAppTheme
import com.walcker.flickly.navigator.MoviesEntry
import com.walcker.flickly.products.movies.strings.LocalStrings
import com.walcker.flickly.products.movies.strings.ProvideStrings
import com.walcker.flickly.products.movies.strings.StringsHolder
import com.walcker.flickly.products.movies.strings.rememberStrings
import com.walcker.movies.core.navigation.NavigatorHolder
import org.koin.compose.koinInject

public data class MoviesEntrypoint(
    private val isDarkTheme: Boolean,
) : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        val platform = platformImpl()
        val lyricist = rememberStrings(currentLanguageTag = platform.languageSystem)
        val moviesEntry = koinInject<MoviesEntry>()

        val strings = LocalStrings.current
        val stringsHolder: StringsHolder = koinInject()
        val navigatorHolder: NavigatorHolder = koinInject()

        LaunchedEffect(navigator, strings) {
            navigatorHolder.setNavigator(navigator)
            stringsHolder.setStrings(strings)
        }

        ProvideStrings(lyricist) {
            MoviesAppTheme(isDarkTheme = isDarkTheme) {
                navigator.replaceAll(moviesEntry.moviesHome())
            }
        }
    }
}