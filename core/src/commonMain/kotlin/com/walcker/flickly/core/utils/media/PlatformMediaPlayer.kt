package com.walcker.flickly.core.utils.media

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun PlatformMediaPlayer(
    url: String,
    modifier: Modifier,
)

