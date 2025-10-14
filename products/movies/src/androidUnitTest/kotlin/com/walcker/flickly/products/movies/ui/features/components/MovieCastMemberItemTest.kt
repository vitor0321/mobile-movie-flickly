package com.walcker.flickly.products.movies.ui.features.components

import com.walcker.flickly.cedarDS.MovieCastMemberItem
import com.walcker.flickly.products.movies.ui.features.DefaultPaparazzi
import com.walcker.flickly.products.movies.ui.features.movieSnapshot
import org.junit.Rule
import kotlin.test.Test

internal class MovieCastMemberItemTest {
    @get:Rule
    val paparazzi = DefaultPaparazzi

    @Test
    fun snapshot() {
        paparazzi.movieSnapshot {
            MovieCastMemberItem(
                imageUrl = "https://example.com/profile.jpg",
                name = "John Doe",
                character = "Main Character"
            )
        }
    }
}