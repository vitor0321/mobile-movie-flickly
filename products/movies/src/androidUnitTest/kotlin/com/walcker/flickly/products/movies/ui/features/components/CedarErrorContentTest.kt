package com.walcker.flickly.products.movies.ui.features.components

import com.walcker.flickly.cedarDS.CedarErrorContent
import com.walcker.flickly.products.movies.ui.features.DefaultPaparazzi
import com.walcker.flickly.products.movies.ui.features.movieSnapshot
import org.junit.Rule
import kotlin.test.Test

internal class CedarErrorContentTest {
    @get:Rule
    val paparazzi = DefaultPaparazzi

    @Test
    fun snapshot() {
        paparazzi.movieSnapshot {
            CedarErrorContent(
                message = "An error occurred while loading movies.",
                onRetry = { },
            )
        }
    }
}