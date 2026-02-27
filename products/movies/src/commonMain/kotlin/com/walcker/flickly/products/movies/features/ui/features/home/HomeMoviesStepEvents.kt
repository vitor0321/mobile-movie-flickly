package com.walcker.flickly.products.movies.features.ui.features.home

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
import com.walcker.flickly.products.movies.features.ui.features.home.components.AlertChangePassword
import com.walcker.flickly.products.movies.strings.MoviesListStrings

@Composable
internal fun HomeMoviesStepEvents(
    model: HomeMoviesStepModel,
    strings: MoviesListStrings,
    onEvent: (HomeMoviesInternalRoute) -> Unit,
    content: @Composable () -> Unit,
) {
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var showChangePasswordDialog by remember { mutableStateOf(false) }

    EventEffect(flow = model.events) { event ->
        when (event) {
            is HomeMoviesInternalEvents.OnError -> errorMessage = event.errorMessage
            is HomeMoviesInternalEvents.OnShowChangePassword -> showChangePasswordDialog = true
        }
    }

    Box(Modifier.fillMaxSize()) {
        content()
        errorMessage?.let { message ->
            CedarErrorContent(
                message = message,
                onRetry = {
                    errorMessage = null
                    onEvent(HomeMoviesInternalRoute.OnRetry)
                },
            )
        }
        if (showChangePasswordDialog) {
            AlertChangePassword(
                strings = strings,
                onDismiss = { showChangePasswordDialog = false },
                onEvent = onEvent,
            )
        }
    }
}