package com.walcker.movies.produto.batSignal.features.ui.features.home

import MediaPlayer
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.walcker.movies.core.step.Step

internal data object HomeAudioStep : Step() {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow


        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
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
                autoPlay = true,
                showControls = true,
            )
        }

    }
}