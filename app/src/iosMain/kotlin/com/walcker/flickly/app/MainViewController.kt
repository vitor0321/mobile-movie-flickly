package com.walcker.flickly.app

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.window.ComposeUIViewController
import cafe.adriel.voyager.navigator.Navigator
import com.walcker.flickly.core.ui.theme.MoviesAppTheme
import com.walcker.flickly.products.movies.entrypoint.MoviesEntrypoint
import platform.UIKit.UIViewController

private val IsDarkThemeState = mutableStateOf(false)

fun mainViewController(isDarkTheme: Boolean = false): UIViewController {
    IsDarkThemeState.value = isDarkTheme

    return ComposeUIViewController {
        MoviesAppTheme {
            Navigator(
                screen = MoviesEntrypoint(isDarkTheme = IsDarkThemeState.value)
            )
        }
    }
}

fun updateTheme(controller: UIViewController, isDarkTheme: Boolean) {
    IsDarkThemeState.value = isDarkTheme
    controller.view.setNeedsDisplay()
}