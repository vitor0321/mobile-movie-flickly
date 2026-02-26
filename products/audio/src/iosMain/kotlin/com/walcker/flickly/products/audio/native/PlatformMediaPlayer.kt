package com.walcker.flickly.products.audio.native

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.viewinterop.UIKitViewController
import kotlinx.cinterop.ExperimentalForeignApi
import platform.AVFoundation.AVLayerVideoGravityResizeAspect
import platform.AVFoundation.AVPlayer
import platform.AVFoundation.pause
import platform.AVKit.AVPlayerViewController
import platform.Foundation.NSURL

@OptIn(ExperimentalComposeUiApi::class, ExperimentalForeignApi::class)
@Composable
internal actual fun PlatformMediaPlayer(
    url: String,
    modifier: Modifier,
) {
    val nsUrl = remember(url) { NSURL.URLWithString(url) } ?: return
    val player = remember(nsUrl) { AVPlayer.playerWithURL(nsUrl) }

    val controller = remember {
        AVPlayerViewController().apply {
            showsPlaybackControls = true
            entersFullScreenWhenPlaybackBegins = false
            exitsFullScreenWhenPlaybackEnds = false
            videoGravity = AVLayerVideoGravityResizeAspect
        }
    }

    DisposableEffect(player) {
        controller.player = player
        onDispose {
            controller.player = null
            player.pause()
        }
    }

    UIKitViewController(
        factory = { controller },
        modifier = modifier,
    )
}

