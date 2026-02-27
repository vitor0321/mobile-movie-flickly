package com.walcker.flickly.core.utils.media

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
expect fun PlatformVideoPlayer(
    youtubeKey: String,
    modifier: Modifier,
)

fun extractYouTubeKey(youtubeKeyOrUrl: String): String = when {
    youtubeKeyOrUrl.contains("youtube.com/embed/") ->
        youtubeKeyOrUrl.substringAfter("youtube.com/embed/").substringBefore("?").substringBefore("&")
    youtubeKeyOrUrl.contains("youtube.com/watch") ->
        youtubeKeyOrUrl.substringAfter("v=").substringBefore("&")
    youtubeKeyOrUrl.contains("youtu.be/") ->
        youtubeKeyOrUrl.substringAfter("youtu.be/").substringBefore("?").substringBefore("&")
    else -> youtubeKeyOrUrl
}