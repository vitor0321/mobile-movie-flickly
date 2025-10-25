package com.walcker.flickly.products.movies.ui.features.components

import androidx.compose.foundation.layout.width
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.walcker.flickly.cedarDS.MovieCastMemberItem
import com.walcker.flickly.cedarDS.MovieRowList
import com.walcker.flickly.products.movies.features.ui.preview.mockData.movieTestData
import com.walcker.flickly.products.movies.ui.features.DefaultPaparazzi
import com.walcker.flickly.products.movies.ui.features.movieSnapshot
import kotlinx.collections.immutable.persistentListOf
import org.junit.Rule
import kotlin.test.Test

internal class MovieRowListTest {
    @get:Rule
    val paparazzi = DefaultPaparazzi

    @Test
    fun snapshot() {
        paparazzi.movieSnapshot {
            MovieRowList(
                items = persistentListOf(
                    movieTestData,
                    movieTestData,
                    movieTestData
                )
            ) { castMember, width ->
                MovieCastMemberItem(
                    modifier = Modifier.width(width.dp),
                    imageUrl = castMember.posterUrl,
                    name = castMember.title,
                    character = castMember.overview
                )
            }
        }
    }
}