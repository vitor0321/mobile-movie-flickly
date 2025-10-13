package com.walcker.movies.produto.batSignal.navigation

import cafe.adriel.voyager.core.screen.Screen
import com.walcker.movies.navigator.BatSignalEntry
import com.walcker.movies.produto.batSignal.features.ui.entrypoint.AudioEntrypoint
import com.walcker.movies.produto.batSignal.features.ui.features.home.HomeAudioStep

internal class AudioEntryImpl : BatSignalEntry {

    override fun batSignalEntryPoint(): Screen = AudioEntrypoint
    override fun batSignalHome(): Screen = HomeAudioStep
}