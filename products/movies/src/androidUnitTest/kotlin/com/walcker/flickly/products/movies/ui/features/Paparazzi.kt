package com.walcker.flickly.products.movies.ui.features

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalInspectionMode
import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import com.android.ide.common.rendering.api.SessionParams.RenderingMode
import com.walcker.flickly.core.theme.MoviesAppTheme

public val DefaultPaparazzi: Paparazzi =
    Paparazzi(
        deviceConfig = DeviceConfig.PIXEL,
        renderingMode = RenderingMode.SHRINK,
        showSystemUi = false,
        appCompatEnabled = false,
    )

public fun Paparazzi.movieSnapshot(
    content: ComposableContent,
) {
    snapshot {
        CompositionLocalProvider(LocalInspectionMode provides true) {
            MoviesAppTheme(isDarkTheme = false){
                Box(
                    modifier = Modifier.background(color = MaterialTheme.colorScheme.background),
                ) {
                    content()
                }
            }
        }
    }
}

internal typealias ComposableContent = @Composable () -> Unit