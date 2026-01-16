package com.walcker.flickly.core.utils.media

import androidx.compose.runtime.Composable

fun formatYouTubeUrl(youtubeKeyOrUrl: String): String =
    if (youtubeKeyOrUrl.contains("youtube.com") || youtubeKeyOrUrl.contains("youtu.be")) {
        youtubeKeyOrUrl
    } else {
        "https://www.youtube.com/watch?v=$youtubeKeyOrUrl"
    }

expect fun openVideoInNativePlayer(url: String)

@Composable
expect fun OpenVideo(url: String)