package com.walcker.flickly.products.movies.ui.features.components

import com.walcker.flickly.cedarDS.CedarTopAppBar
import com.walcker.flickly.products.movies.ui.features.DefaultPaparazzi
import com.walcker.flickly.products.movies.ui.features.movieSnapshot
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.ArrowLeft
import compose.icons.fontawesomeicons.solid.Umbrella
import org.junit.Rule
import kotlin.test.Test

internal class CedarTopAppBar {
    @get:Rule
    val paparazzi = DefaultPaparazzi

    @Test
    fun snapshot() {
        paparazzi.movieSnapshot {
            CedarTopAppBar(
                title = "Movie Detail",
                icon = FontAwesomeIcons.Solid.ArrowLeft,
                iconAudio = FontAwesomeIcons.Solid.Umbrella,
                onAudio = {},
                onNavigationBack = {}
            )
        }
    }
}