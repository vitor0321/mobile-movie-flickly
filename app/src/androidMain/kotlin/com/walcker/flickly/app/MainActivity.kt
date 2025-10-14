package com.walcker.flickly.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowInsetsControllerCompat
import cafe.adriel.voyager.navigator.Navigator
import com.walcker.flickly.core.theme.MoviesAppTheme
import com.walcker.flickly.products.movies.features.ui.features.entrypoint.MoviesEntrypoint

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            val view = LocalView.current
            val dark = isSystemInDarkTheme()
            SideEffect {
                WindowInsetsControllerCompat(window, view)
                    .isAppearanceLightStatusBars = !dark
            }
            MoviesAppTheme {
                Navigator(
                    screen = MoviesEntrypoint(isDarkTheme = dark)
                )
            }
        }
    }
}