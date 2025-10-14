package com.walcker.flickly.products.audio.ui.features.home

import MediaPlayer
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.walcker.flickly.cedarDS.MovieTopAppBar
import com.walcker.flickly.core.step.Step

internal data object HomeAudioStep : Step() {

    @Composable
    override fun Content() {

        Scaffold(
            topBar = {
                MovieTopAppBar(
                    title = "Bíblia ",
                )
            }
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ) {
                Text("Home Screen Content Here - Replace this with your actual UI code")
                MediaPlayer(
                    modifier = Modifier.fillMaxWidth(),
                    url = "https://commondatastorage.googleapis.com/codeskulptor-demos/DDR_assets/Kangaroo_MusiQue_-_The_Neverwritten_Role_Playing_Game.mp3",
                    startTime = Color.Black,
                    endTime = Color.Black,
                    volumeIconColor = Color.Black,
                    playIconColor = Color.Blue,
                    sliderTrackColor = Color.LightGray,
                    sliderIndicatorColor = Color.Blue,
                    headers = mapOf(),
                    autoPlay = false,
                    showControls = true,
                )
            }
        }
    }
}