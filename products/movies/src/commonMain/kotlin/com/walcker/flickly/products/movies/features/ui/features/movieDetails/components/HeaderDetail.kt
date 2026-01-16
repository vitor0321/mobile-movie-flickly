package com.walcker.flickly.products.movies.features.ui.features.movieDetails.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.walcker.flickly.cedarDS.CedarAsyncImage
import com.walcker.flickly.core.ui.theme.MoviesAppTheme

@Composable
internal fun HeaderDetail(
    modifier: Modifier = Modifier,
    posterUrl: String,
) {
    Surface(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        shape = MaterialTheme.shapes.large,
    ) {
        CedarAsyncImage(
            imageUrl = posterUrl,
            modifier = Modifier.clip(MaterialTheme.shapes.large),
        )
    }
}

@Preview
@Composable
private fun Preview() {
    MoviesAppTheme {
        HeaderDetail(
            posterUrl = "https://image.tmdb.org/t/p/w500/1LRLLWGvs5sZdTzuMqLEahb88Pc.jpg"
        )
    }
}