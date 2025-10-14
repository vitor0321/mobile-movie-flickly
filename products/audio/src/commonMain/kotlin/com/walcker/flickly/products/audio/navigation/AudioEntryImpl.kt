package com.walcker.flickly.products.audio.navigation

import cafe.adriel.voyager.core.screen.Screen
import com.walcker.flickly.navigator.BatSignalEntry
import com.walcker.flickly.products.audio.ui.entrypoint.AudioEntrypoint
import com.walcker.flickly.products.audio.ui.features.home.HomeAudioStep

internal class AudioEntryImpl : BatSignalEntry {

    override fun batSignalEntryPoint(): Screen = AudioEntrypoint
    override fun batSignalHome(): Screen = HomeAudioStep
}