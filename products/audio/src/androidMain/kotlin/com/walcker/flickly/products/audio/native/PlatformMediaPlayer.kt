package com.walcker.flickly.products.audio.native

import android.media.MediaPlayer
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Pause
import compose.icons.fontawesomeicons.solid.Play
import kotlinx.coroutines.delay

@Composable
internal actual fun PlatformMediaPlayer(
    url: String,
    modifier: Modifier,
) {
    val player = remember(url) {
        MediaPlayer().apply {
            setDataSource(url)
            prepareAsync()
        }
    }

    var isReady by remember { mutableStateOf(false) }
    var isPlaying by remember { mutableStateOf(false) }
    var currentMs by remember { mutableFloatStateOf(0f) }
    var durationMs by remember { mutableFloatStateOf(0f) }
    var isSeeking by remember { mutableStateOf(false) }

    DisposableEffect(player) {
        player.setOnPreparedListener { mp ->
            isReady = true
            durationMs = mp.duration.toFloat()
        }
        player.setOnCompletionListener {
            isPlaying = false
            currentMs = 0f
        }
        onDispose {
            player.stop()
            player.release()
        }
    }

    LaunchedEffect(isPlaying, isReady) {
        while (isPlaying && isReady) {
            if (!isSeeking) currentMs = player.currentPosition.toFloat()
            delay(500)
        }
    }

    val controlColor = MaterialTheme.colorScheme.onSecondary

    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center,
    ) {
        if (!isReady) {
            CircularProgressIndicator(
                color = controlColor,
            )
        } else {
            Column(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    IconButton(
                        onClick = {
                            if (isPlaying) {
                                player.pause()
                                isPlaying = false
                            } else {
                                player.start()
                                isPlaying = true
                            }
                        },
                    ) {
                        Icon(
                            imageVector = if (isPlaying) FontAwesomeIcons.Solid.Pause else FontAwesomeIcons.Solid.Play,
                            contentDescription = if (isPlaying) "Pause" else "Play",
                            tint = controlColor,
                            modifier = Modifier.size(24.dp),
                        )
                    }
                }

                if (durationMs > 0f) {
                    Slider(
                        value = currentMs.coerceIn(0f, durationMs),
                        onValueChange = { value ->
                            isSeeking = true
                            currentMs = value
                        },
                        onValueChangeFinished = {
                            player.seekTo(currentMs.toInt())
                            isSeeking = false
                        },
                        valueRange = 0f..durationMs,
                        colors = SliderDefaults.colors(
                            thumbColor = controlColor,
                            activeTrackColor = controlColor,
                            inactiveTrackColor = controlColor.copy(alpha = 0.3f),
                        ),
                        modifier = Modifier.fillMaxWidth(),
                    )

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                    ) {
                        Text(
                            text = currentMs.toInt().msToTimeLabel(),
                            style = MaterialTheme.typography.bodySmall,
                            color = controlColor,
                        )
                        Text(
                            text = durationMs.toInt().msToTimeLabel(),
                            style = MaterialTheme.typography.bodySmall,
                            color = controlColor,
                        )
                    }
                }
            }
        }
    }
}

private fun Int.msToTimeLabel(): String {
    val totalSec = (this / 1000).coerceAtLeast(0)
    val min = totalSec / 60
    val sec = totalSec % 60
    return "$min:${if (sec < 10) "0$sec" else "$sec"}"
}
