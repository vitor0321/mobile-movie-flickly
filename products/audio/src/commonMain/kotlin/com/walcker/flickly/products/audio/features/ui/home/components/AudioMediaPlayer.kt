package com.walcker.flickly.products.audio.features.ui.home.components

import MediaPlayer
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
internal fun AudioMediaPlayer(
    audioUrl: String,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp)
            .background(
                MaterialTheme.colorScheme.secondary,
                MaterialTheme.shapes.medium
            )
            .padding(8.dp),
        contentAlignment = Alignment.Center,
    ) {
        MediaPlayer(
            url = audioUrl,
            modifier = Modifier
                .height(70.dp)
                .fillMaxWidth(),
            headers = emptyMap(),
            startTime = MaterialTheme.colorScheme.onSecondary,
            endTime = MaterialTheme.colorScheme.onSecondary,
            volumeIconColor = MaterialTheme.colorScheme.onSecondary,
            playIconColor = MaterialTheme.colorScheme.onSecondary,
            sliderTrackColor = MaterialTheme.colorScheme.onSecondary,
            sliderIndicatorColor = MaterialTheme.colorScheme.onSecondary,
            autoPlay = false,
            showControls = true,
        )
    }
}