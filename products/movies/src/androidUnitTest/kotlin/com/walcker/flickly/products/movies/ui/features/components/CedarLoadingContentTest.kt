package com.walcker.flickly.products.movies.ui.features.components

import com.walcker.flickly.cedarDS.CedarLoadingContent
import com.walcker.flickly.products.movies.ui.features.DefaultPaparazzi
import com.walcker.flickly.products.movies.ui.features.movieSnapshot
import org.junit.Rule
import kotlin.test.Test

internal class CedarLoadingContentTest {
    @get:Rule
    val paparazzi = DefaultPaparazzi

    @Test
    fun snapshot() {
        paparazzi.movieSnapshot {
            CedarLoadingContent()
        }
    }
}