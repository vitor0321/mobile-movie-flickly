package com.walcker.flickly.products.movies.features.ui.features.home.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.carousel.HorizontalMultiBrowseCarousel
import androidx.compose.material3.carousel.rememberCarouselState
import androidx.compose.runtime.Composable
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
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HeaderSuccessContent(
    modifier: Modifier = Modifier,
    movies: ImmutableList<Movie>,
    onPosterClick: (movieId: Int) -> Unit,
) {
    val carouselState = rememberCarouselState { movies.size }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        HorizontalMultiBrowseCarousel(
            state = carouselState,
            modifier = Modifier
                .fillMaxWidth(),
            preferredItemWidth = 250.dp,
            itemSpacing = 8.dp,
            contentPadding = PaddingValues(horizontal = 16.dp)
        ) { i ->
            val movie = movies[i]
            Card(
                modifier = Modifier
                    .width(300.dp)
                    .height(350.dp)
                    .clickable(
                        onClick = { onPosterClick(movie.id) }
                    ),
                shape = RoundedCornerShape(12.dp),
            ) {
                CedarAsyncImage(
                    imageUrl = movie.posterUrl,
                    modifier = Modifier.clip(MaterialTheme.shapes.large)
                )
            }
        }

        MovieDotsIndicator(
            modifier = Modifier.padding(top = 8.dp),
            count = movies.size,
            selectedIndex = carouselState.currentItem
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