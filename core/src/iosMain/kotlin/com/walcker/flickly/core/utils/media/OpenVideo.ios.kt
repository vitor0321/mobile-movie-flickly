package com.walcker.flickly.core.utils.media

import androidx.compose.runtime.Composable

@Composable
public actual fun OpenVideo(url: String) {
    androidx.compose.runtime.SideEffect {
        openVideoInNativePlayer(url)
    }
}