package com.walcker.flickly.products.audio.features.ui.chapter.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.walcker.flickly.products.audio.native.PlatformMediaPlayer

private val PLAYER_HEIGHT = 150.dp

@Composable
internal fun AudioMediaPlayer(
    audioUrl: String?,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .height(PLAYER_HEIGHT),
        contentAlignment = Alignment.Center,
    ) {
        audioUrl?.let {
            PlatformMediaPlayer(
                url = it,
                modifier = modifier
                    .fillMaxWidth()
                    .height(PLAYER_HEIGHT)
                    .padding(horizontal = 16.dp),
            )
        }
    }
}