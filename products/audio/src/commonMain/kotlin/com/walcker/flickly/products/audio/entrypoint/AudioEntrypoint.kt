package com.walcker.flickly.products.audio.entrypoint

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.walcker.flickly.core.navigation.NavigatorHolder
import com.walcker.flickly.core.platformImpl
import com.walcker.flickly.core.strings.Locales
import com.walcker.flickly.navigator.AudioEntry
import com.walcker.flickly.products.audio.strings.AudioStringsHolder
import com.walcker.flickly.products.audio.strings.LocalAudioStrings
import com.walcker.flickly.products.audio.strings.ProvideAudioStrings
import com.walcker.flickly.products.audio.strings.rememberAudioStrings
import org.koin.compose.koinInject

internal data object AudioEntrypoint : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val audioEntry = koinInject<AudioEntry>()

        val platform = platformImpl()
        val systemLanguage = platform.languageSystem

        val validLanguage = when (systemLanguage.lowercase()) {
            "pt", "pt-br", "pt_br" -> Locales.PT
            "en", "en-us", "en_us" -> Locales.EN
            "ur", "ur-pk", "ur_pk" -> Locales.UR
            else -> Locales.EN
        }

        val lyricist = rememberAudioStrings(languageTag = validLanguage)

        val stringsHolder: AudioStringsHolder = koinInject()
        val navigatorHolder: NavigatorHolder = koinInject()

        ProvideAudioStrings(lyricist) {
            val strings = LocalAudioStrings.current

            LaunchedEffect(navigator, strings) {
                navigatorHolder.setNavigator(navigator)
                stringsHolder.setStrings(strings)
            }

            LaunchedEffect(Unit) {
                navigator.replace(audioEntry.audioHome())
            }
        }
    }
}