package com.walcker.flickly.products.audio.native

import MediaPlayer
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal actual fun PlatformMediaPlayer(
    url: String,
    modifier: Modifier,
) {
    MediaPlayer(
        url = url,
        modifier = modifier,
        headers = emptyMap(),
        startTime = MaterialTheme.colorScheme.onSecondary,
        endTime = MaterialTheme.colorScheme.onSecondary,
        volumeIconColor = MaterialTheme.colorScheme.onSecondary,
        playIconColor = MaterialTheme.colorScheme.onSecondary,
        sliderTrackColor = MaterialTheme.colorScheme.onSecondary,
        sliderIndicatorColor = MaterialTheme.colorScheme.onSecondary,
        autoPlay = false,
        showControls = true,
    )
}

