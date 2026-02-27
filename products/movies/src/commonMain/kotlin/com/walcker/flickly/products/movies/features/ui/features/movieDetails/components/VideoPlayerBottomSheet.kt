package com.walcker.flickly.products.movies.features.ui.features.movieDetails.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.walcker.flickly.core.utils.media.PlatformVideoPlayer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun VideoPlayerBottomSheet(
    youtubeKey: String,
    onDismiss: () -> Unit,
) {
    ModalBottomSheet(
        onDismissRequest = onDismiss,
        sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true),
    ) {
        PlatformVideoPlayer(
            modifier = Modifier
                .fillMaxWidth()
                .aspectRatio(16f / 9f),
            youtubeKey = youtubeKey,
        )
        Spacer(modifier = Modifier.height(32.dp))
    }
}