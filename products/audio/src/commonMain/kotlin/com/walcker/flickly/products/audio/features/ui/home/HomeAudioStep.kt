package com.walcker.flickly.products.audio.features.ui.home

import MediaPlayer
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.koin.koinScreenModel
import com.walcker.flickly.cedarDS.CedarErrorContent
import com.walcker.flickly.cedarDS.CedarLoadingContent
import com.walcker.flickly.cedarDS.CedarTopAppBar
import com.walcker.flickly.core.ui.step.Step
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.ArrowLeft

internal data object HomeAudioStep : Step() {

    @Composable
    override fun Content() {
        val model = koinScreenModel<HomeAudioStepModel>()
        val state by model.state.collectAsState()

        HomeAudioStepContent(
            state = state,
            onEvent = model::onEvent,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeAudioStepContent(
    state: HomeAudioState,
    onEvent: (HomeAudioInternalRoute) -> Unit,
) {
    Scaffold(
        topBar = {
            CedarTopAppBar(
                title =  "Audio Bible",
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                icon = FontAwesomeIcons.Solid.ArrowLeft,
                onNavigationBack = { onEvent(HomeAudioInternalRoute.OnPopBackStack) },
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            MaterialTheme.colorScheme.surfaceVariant,
                            MaterialTheme.colorScheme.surface
                        )
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                state.errorMessage?.let {
                    CedarErrorContent(
                        message = state.errorMessage,
                        onRetry = { onEvent(HomeAudioInternalRoute.OnRetry) }
                    )
                }
                state.audioUrl?.let {
                    MediaPlayer(
                        url = state.audioUrl,
                        modifier = Modifier.fillMaxWidth(),
                        headers = mapOf(
                            "Authorization" to "Bearer your_token",
                            "Custom-Header" to "YourValue"
                        ),
                        startTime = Color.Black,
                        endTime = Color.Black,
                        volumeIconColor = Color.Black,
                        playIconColor = Color.Blue,
                        sliderTrackColor = Color.LightGray,
                        sliderIndicatorColor = Color.Blue,
                        autoPlay = false,
                        showControls = true,
                    )
                }
                if (state.loading)
                    CedarLoadingContent()
            }
        }
    }
}