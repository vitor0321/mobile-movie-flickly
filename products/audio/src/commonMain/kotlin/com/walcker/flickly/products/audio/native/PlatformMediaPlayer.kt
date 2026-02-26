package com.walcker.flickly.products.audio.native

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
internal expect fun PlatformMediaPlayer(
    url: String,
    modifier: Modifier,
)

