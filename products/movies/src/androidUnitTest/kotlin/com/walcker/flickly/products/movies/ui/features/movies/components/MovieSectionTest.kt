package com.walcker.flickly.products.movies.ui.features.movies.components

import com.walcker.flickly.products.movies.features.ui.features.home.components.MovieSection
import com.walcker.flickly.products.movies.features.ui.preview.mockData.movieTestData
import com.walcker.flickly.products.movies.ui.features.DefaultPaparazzi
import com.walcker.flickly.products.movies.ui.features.movieSnapshot
import kotlinx.collections.immutable.toImmutableList
import org.junit.Rule
import kotlin.test.Test

internal class MovieSectionTest {
    @get:Rule
    val paparazzi = DefaultPaparazzi

    @Test
    fun snapshot() {
        paparazzi.movieSnapshot {
            MovieSection(
                title = "Filmes Populares",
                movies = List(10) {
                    movieTestData
                }.toImmutableList(),
                onPosterClick = {}
            )
        }
    }
}