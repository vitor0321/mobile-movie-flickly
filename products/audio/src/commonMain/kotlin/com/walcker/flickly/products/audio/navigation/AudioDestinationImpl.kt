package com.walcker.flickly.products.audio.navigation

import cafe.adriel.voyager.core.screen.Screen
import com.walcker.flickly.navigator.AudioDestination
import com.walcker.flickly.products.audio.entrypoint.AudioEntrypoint
import com.walcker.flickly.products.audio.features.ui.home.HomeAudioStep

internal class AudioDestinationImpl : AudioDestination {

    override fun audioEntryPoint(): Screen = AudioEntrypoint
    override fun audioHome(): Screen = HomeAudioStep
}