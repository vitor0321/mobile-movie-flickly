package com.walcker.flickly.products.movies.features.ui.features.entrypoint

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.walcker.flickly.core.navigation.NavigatorHolder
import com.walcker.flickly.core.platformImpl
import com.walcker.flickly.core.strings.Locales
import com.walcker.flickly.core.ui.theme.MoviesAppTheme
import com.walcker.flickly.navigator.MoviesEntry
import com.walcker.flickly.products.movies.strings.LocalMoviesStrings
import com.walcker.flickly.products.movies.strings.MoviesStringsHolder
import com.walcker.flickly.products.movies.strings.ProvideMoviesStrings
import com.walcker.flickly.products.movies.strings.rememberMoviesStrings
import org.koin.compose.koinInject

public data class MoviesEntrypoint(
    private val isDarkTheme: Boolean,
) : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow

        val platform = platformImpl()

        val validLanguage = when (platform.languageSystem.lowercase()) {
            "pt", "pt-br", "pt_br" -> Locales.PT
            "en", "en-us", "en_us" -> Locales.EN
            "ur", "ur-pk", "ur_pk" -> Locales.UR
            else -> { Locales.EN }
        }

        val lyricist = rememberMoviesStrings(languageTag = validLanguage)
        val moviesEntry = koinInject<MoviesEntry>()

        val stringsHolder: MoviesStringsHolder = koinInject()
        val navigatorHolder: NavigatorHolder = koinInject()

        ProvideMoviesStrings(lyricist) {
            val strings = LocalMoviesStrings.current

            LaunchedEffect(navigator, strings) {
                navigatorHolder.setNavigator(navigator)
                stringsHolder.setStrings(strings)
                println("📝 Strings updated: appName = ${strings.appName}")
            }

            MoviesAppTheme(isDarkTheme = isDarkTheme) {
                navigator.replaceAll(moviesEntry.moviesHome())
            }
        }
    }
}