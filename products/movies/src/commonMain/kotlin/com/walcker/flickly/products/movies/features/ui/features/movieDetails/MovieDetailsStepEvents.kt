package com.walcker.flickly.products.movies.features.ui.features.movieDetails

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.walcker.flickly.cedarDS.CedarErrorContent
import com.walcker.flickly.core.navigation.EventEffect

@Composable
internal fun MovieDetailsStepEvents(
    model: MovieDetailsStepModel,
    onEvent: (MovieDetailsInternalRoute) -> Unit,
    content: @Composable () -> Unit,
) {
    var errorMessage by remember { mutableStateOf<String?>(null) }

    EventEffect(flow = model.events) { event ->
        when (event) {
            is MovieDetailsInternalEvents.OnError -> errorMessage = event.errorMessage
        }
    }

    Box(Modifier.fillMaxSize()) {
        content()
        errorMessage?.let { message ->
            CedarErrorContent(
                message = message,
                onRetry = {
                    errorMessage = null
                    onEvent(MovieDetailsInternalRoute.OnRetry)
                },
            )
        }
    }
}