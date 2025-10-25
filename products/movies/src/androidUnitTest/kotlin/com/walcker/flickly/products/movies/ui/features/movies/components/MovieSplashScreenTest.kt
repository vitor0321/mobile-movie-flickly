package com.walcker.flickly.products.movies.ui.features.movies.components

import com.walcker.flickly.products.movies.features.ui.features.home.components.MovieSplashScreen
import com.walcker.flickly.products.movies.ui.features.DefaultPaparazzi
import com.walcker.flickly.products.movies.ui.features.movieSnapshot
import org.junit.Rule
import kotlin.test.Test

internal class MovieSplashScreenTest {
    @get:Rule
    val paparazzi = DefaultPaparazzi

    @Test
    fun snapshot() {
        paparazzi.movieSnapshot {
            MovieSplashScreen()
        }
    }
}