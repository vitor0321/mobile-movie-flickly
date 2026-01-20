package com.walcker.flickly.core.utils.media

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat

private fun openVideoWithContext(context: Context, url: String) {
    val formattedUrl = formatYouTubeUrl(url)

    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(formattedUrl)).apply {
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }

    val youtubePackage = "com.google.android.youtube"
    val isYouTubeVideo =
        formattedUrl.contains("youtube.com") || formattedUrl.contains("youtu.be")

    if (isYouTubeVideo) {
        val youtubeIntent = context.packageManager?.getLaunchIntentForPackage(youtubePackage)
        if (youtubeIntent != null) {
            intent.setPackage(youtubePackage)
        }
    }

    try {
        ContextCompat.startActivity(context, intent, null)
    } catch (e: Exception) {
        android.util.Log.e("VideoLauncher", "Failed to open video: ${e.message}")
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(formattedUrl)).apply {
            addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        }
        try {
            ContextCompat.startActivity(context, browserIntent, null)
        } catch (e2: Exception) {
            android.util.Log.e("VideoLauncher", "Failed to open browser: ${e2.message}")
            Toast.makeText(
                context,
                "Não foi possível abrir o vídeo",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}


public object VideoLauncherContext {
    private var _context: Context? = null

    fun init(context: Context) {
        _context = context.applicationContext
    }

    fun getContext(): Context? = _context
}

public actual fun openVideoInNativePlayer(url: String) {
    val context = VideoLauncherContext.getContext() ?: run {
        android.util.Log.e("VideoLauncher", "Context not initialized. Call VideoLauncherContext.init(context) in your Application or MainActivity")
        return
    }
    openVideoWithContext(context, url)
}

@Composable
public actual fun OpenVideo(url: String) {
    val context = LocalContext.current
    SideEffect {
        openVideoWithContext(context, url)
    }
}