package com.walcker.flickly.cedarDS

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.walcker.flickly.core.ui.theme.MoviesAppTheme
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Bug
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
public fun CedarAsyncImage(
    modifier: Modifier = Modifier,
    imageUrl: String?,
) {
    val loading = remember { mutableStateOf(false) }
    val error = remember { mutableStateOf(false) }

    Box(modifier = modifier.fillMaxSize()) {
        AsyncImage(
            modifier = Modifier.fillMaxSize(),
            model = imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            onLoading = {
                error.value = false
                loading.value = true
            },
            onError = {
                error.value = true
                loading.value = false
            },
            onSuccess = {
                error.value = false
                loading.value = false
            }
        )

        if (loading.value) {
            Box(
                modifier = Modifier
                    .background(color = White)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator(color = Color.LightGray)
            }
        }

        if (error.value) {
            Box(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(24.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Icon(
                            modifier = Modifier.size(60.dp),
                            imageVector = FontAwesomeIcons.Solid.Bug,
                            contentDescription = null,
                            tint = Color.Red,
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    MoviesAppTheme {
        Card(
            modifier = Modifier
                .width(140.dp)
                .height(210.dp),
        ) {
            CedarAsyncImage(imageUrl = "https://image.tmdb.org/t/p/w500/bOGkgRGdhrBYJSLpXaxhXVstddV.jpg")
        }
    }
}