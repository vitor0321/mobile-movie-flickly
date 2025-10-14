package com.walcker.flickly.products.audio.ui.entrypoint

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.walcker.flickly.navigator.BatSignalEntry
import org.koin.compose.koinInject

internal data object AudioEntrypoint : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val batSignalEntry = koinInject<BatSignalEntry>()

        LaunchedEffect(Unit) {
            navigator.replace(batSignalEntry.batSignalHome())
        }
    }
}