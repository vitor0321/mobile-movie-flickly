package com.walcker.movies.produto.movies.ui.features.movies.components

import com.walcker.movies.produto.movies.features.ui.features.home.components.HeaderSuccessContent
import com.walcker.movies.produto.movies.features.ui.preview.mockData.movieTestData
import com.walcker.movies.produto.movies.utils.DefaultPaparazzi
import com.walcker.movies.produto.movies.utils.movieSnapshot
import kotlinx.collections.immutable.toImmutableList
import org.junit.Rule
import kotlin.test.Test

internal class HeaderSuccessContentTest {
    @get:Rule
    val paparazzi = DefaultPaparazzi

    @Test
    fun snapshot() {
        paparazzi.movieSnapshot {
            HeaderSuccessContent(
                movies = List(5) {
                    movieTestData
                }.toImmutableList(),
                onPosterClick = {},
            )
        }
    }
}