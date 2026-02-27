package com.walcker.flickly.core.utils.media

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.LocalLifecycleOwner
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@Composable
actual fun PlatformVideoPlayer(
    youtubeKey: String,
    modifier: Modifier,
) {
    val lifecycleOwner = LocalLifecycleOwner.current
    val key = remember(youtubeKey) { youtubeKey }

    AndroidView(
        factory = { ctx ->
            YouTubePlayerView(ctx).apply {
                lifecycleOwner.lifecycle.addObserver(this)
                addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        youTubePlayer.loadVideo(key, 0f)
                    }
                })
            }
        },
        modifier = modifier,
    )
}