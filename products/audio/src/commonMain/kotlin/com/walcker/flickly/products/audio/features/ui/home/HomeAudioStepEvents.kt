package com.walcker.flickly.products.audio.features.ui.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.walcker.flickly.cedarDS.CedarErrorContent
import com.walcker.flickly.core.navigation.EventEffect
import com.walcker.flickly.products.audio.features.ui.home.components.AlertChangePassword
import com.walcker.flickly.products.audio.strings.HomeAudioStrings
import kotlinx.coroutines.launch

@Composable
internal fun HomeMoviesStepEvents(
    model: HomeAudioStepModel,
    strings: HomeAudioStrings,
    onEvent: (HomeAudioInternalRoute) -> Unit,
    content: @Composable () -> Unit,
) {
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var showChangePasswordDialog by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val currentStrings by rememberUpdatedState(strings)

    EventEffect(flow = model.events) { event ->
        when (event) {
            is HomeAudioInternalEvents.OnError -> errorMessage = event.errorMessage
            is HomeAudioInternalEvents.OnShowChangePassword -> showChangePasswordDialog = true
            is HomeAudioInternalEvents.OnSamePassword -> scope.launch {
                snackbarHostState.showSnackbar(currentStrings.samePasswordToast)
            }
            is HomeAudioInternalEvents.OnPasswordChangedSuccess -> {
                showChangePasswordDialog = false
                scope.launch {
                    snackbarHostState.showSnackbar(currentStrings.passwordChangedToast)
                }
            }
        }
    }

    Box(Modifier.fillMaxSize()) {
        content()
        errorMessage?.let { message ->
            CedarErrorContent(
                message = message,
                onRetry = {
                    errorMessage = null
                    onEvent(HomeAudioInternalRoute.OnRetry)
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
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.align(Alignment.BottomCenter).padding(bottom = 16.dp),
            snackbar = { Snackbar(it) },
        )
    }
}