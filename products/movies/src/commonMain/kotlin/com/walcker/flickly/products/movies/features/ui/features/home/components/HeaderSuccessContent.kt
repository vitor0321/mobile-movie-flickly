package com.walcker.flickly.products.movies.features.ui.features.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.walcker.flickly.cedarDS.CedarAsyncImage
import com.walcker.flickly.cedarDS.MovieDotsIndicator
import com.walcker.flickly.core.ui.theme.MoviesAppTheme
import com.walcker.flickly.products.movies.features.domain.models.Movie
import com.walcker.flickly.products.movies.features.ui.preview.mockData.movieTestData
import com.walcker.flickly.products.movies.handle.getCenterItemIndex
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Composable
internal fun HeaderSuccessContent(
    modifier: Modifier = Modifier,
    movies: ImmutableList<Movie>,
    onPosterClick: (movieId: Int) -> Unit,
) {
    val listState = rememberLazyListState()
    val selectedIndex by remember {
        derivedStateOf { getCenterItemIndex(listState) }
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        LazyRow(
            contentPadding = PaddingValues(horizontal = 16.dp),
            state = listState,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            itemsIndexed(movies) { index, movie ->
                Card(
                    modifier = Modifier
                        .width(300.dp)
                        .height(350.dp),
                    shape = RoundedCornerShape(12.dp),
                ) {
                    CedarAsyncImage(
                        imageUrl = movie.posterUrl,
                        modifier = Modifier
                            .clip(MaterialTheme.shapes.large)
                            .clickable(
                                enabled = true,
                                onClick = { onPosterClick(movie.id) },
                            )
                    )
                }
            }
        }
        MovieDotsIndicator(
            modifier = Modifier.padding(top = 8.dp),
            count = movies.size,
            selectedIndex = selectedIndex
        )
    }
}

@Preview
@Composable
private fun Preview() {
    MoviesAppTheme {
        HeaderSuccessContent(
            movies = List(5) {
                movieTestData
            }.toImmutableList(),
            onPosterClick = {},
        )
    }
}