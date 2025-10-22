package com.walcker.flickly.core.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
public fun MoviesAppTheme(
    isDarkTheme: Boolean = true,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (isDarkTheme) MoviesDarkColorScheme else MoviesLightColorScheme,
        typography = MoviesTypography(),
        shapes = MoviesShapes,
        content = content
    )
}