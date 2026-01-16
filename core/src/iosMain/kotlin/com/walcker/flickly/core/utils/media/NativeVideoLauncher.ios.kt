package com.walcker.flickly.core.utils.media

import platform.Foundation.NSURL
import platform.UIKit.UIApplication


public actual fun openVideoInNativePlayer(url: String) {
    val formattedUrl = formatYouTubeUrl(url)
    val nsUrl = NSURL(string = formattedUrl)

    val app = UIApplication.sharedApplication
    app.openURL(nsUrl)
}