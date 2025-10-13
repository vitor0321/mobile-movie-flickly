package com.walcker.movies.produto.movies.ui.components

import com.walcker.movies.produto.movies.features.ui.components.MovieTopAppBar
import com.walcker.movies.produto.movies.utils.DefaultPaparazzi
import com.walcker.movies.produto.movies.utils.movieSnapshot
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.ArrowLeft
import compose.icons.fontawesomeicons.solid.Umbrella
import org.junit.Rule
import kotlin.test.Test

internal class MovieTopAppBarTest {
    @get:Rule
    val paparazzi = DefaultPaparazzi

    @Test
    fun snapshot() {
        paparazzi.movieSnapshot {
            MovieTopAppBar(
                title = "Movie Detail",
                icon = FontAwesomeIcons.Solid.ArrowLeft,
                iconBatSignal = FontAwesomeIcons.Solid.Umbrella,
                onBatSignal = {},
                onNavigationBack = {}
            )
        }
    }
}