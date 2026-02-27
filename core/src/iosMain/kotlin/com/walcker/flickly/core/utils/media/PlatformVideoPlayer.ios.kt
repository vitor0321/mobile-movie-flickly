package com.walcker.flickly.core.utils.media

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.interop.UIKitView
import kotlinx.cinterop.ExperimentalForeignApi
import platform.CoreGraphics.CGRectMake
import platform.Foundation.NSURL
import platform.WebKit.WKWebView
import platform.WebKit.WKWebViewConfiguration
import platform.WebKit.javaScriptEnabled

@OptIn(ExperimentalForeignApi::class)
@Composable
actual fun PlatformVideoPlayer(
    youtubeKey: String,
    modifier: Modifier,
) {
    UIKitView(
        factory = {
            val config = WKWebViewConfiguration().apply {
                allowsInlineMediaPlayback = true
                // 0uL = no media type requires user action (allows autoplay / inline)
                mediaTypesRequiringUserActionForPlayback = 0uL
                allowsAirPlayForMediaPlayback = true
                allowsPictureInPictureMediaPlayback = true
                // Explicitly enable JavaScript – required for the IFrame Player API
                preferences.javaScriptEnabled = true
            }

            WKWebView(
                frame = CGRectMake(0.0, 0.0, 390.0, 220.0),
                configuration = config,
            ).apply {
                scrollView.scrollEnabled = false
                scrollView.bounces = false
                // No custom userAgent – let WKWebView use its default so YouTube
                // can serve the correct player scripts.

                // Load with youtube.com as base URL so the page origin matches the
                // `origin` parameter inside the IFrame Player config, which is the
                // root cause of errors 150 / 153 when they mismatch.
                loadHTMLString(
                    youtubePlayerHtml(youtubeKey),
                    NSURL.URLWithString("https://www.youtube.com"),
                )
            }
        },
        modifier = modifier,
    )
}

// ---------------------------------------------------------------------------
// HTML helper
// ---------------------------------------------------------------------------

private fun youtubePlayerHtml(videoId: String): String = """
    <!DOCTYPE html>
    <html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0">
        <style>
            *, *::before, *::after { box-sizing: border-box; }
            html, body {
                margin: 0; padding: 0;
                width: 100%; height: 100%;
                background: #000;
                overflow: hidden;
            }
            #player { width: 100%; height: 100%; }
        </style>
    </head>
    <body>
        <div id="player"></div>

        <!-- Load the IFrame Player API asynchronously -->
        <script src="https://www.youtube.com/iframe_api"></script>
        <script>
            function onYouTubeIframeAPIReady() {
                new YT.Player('player', {
                    videoId: '$videoId',
                    playerVars: {
                        playsinline : 1,
                        controls   : 1,
                        rel        : 0,
                        fs         : 1,
                        modestbranding: 1,
                        // origin MUST match the base URL supplied to loadHTMLString
                        origin     : 'https://www.youtube.com'
                    },
                    events: {
                        onReady: function(e) { console.log('YT player ready'); },
                        onError: function(e) { console.log('YT error: ' + e.data); }
                    }
                });
            }
        </script>
    </body>
    </html>
""".trimIndent()
