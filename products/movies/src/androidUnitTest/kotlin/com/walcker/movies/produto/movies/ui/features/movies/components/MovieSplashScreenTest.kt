package com.walcker.movies.produto.movies.ui.features.movies.components

import com.walcker.movies.produto.movies.features.ui.features.home.components.MovieSplashScreen
import com.walcker.movies.produto.movies.utils.DefaultPaparazzi
import com.walcker.movies.produto.movies.utils.movieSnapshot
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