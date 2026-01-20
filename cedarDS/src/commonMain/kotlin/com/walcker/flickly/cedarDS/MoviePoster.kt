package com.walcker.flickly.cedarDS

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.walcker.flickly.core.ui.theme.MoviesAppTheme

@Composable
public fun MoviePoster(
    modifier: Modifier = Modifier,
    title: String,
    imageUrl: String?,
    onPosterClick: () -> Unit,
) {
    Surface(
        modifier = modifier
            .width(140.dp)
            .height(230.dp),
        color = MaterialTheme.colorScheme.background
        ) {
        Column(
            modifier = Modifier
                .clickable { onPosterClick() }
                .width(140.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Card(
                modifier = Modifier
                    .width(140.dp)
                    .height(210.dp),
                shape = RoundedCornerShape(12.dp),
            ) {
                CedarAsyncImage(imageUrl = imageUrl)
            }

            Text(
                text = title,
                overflow = TextOverflow.Ellipsis,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 1,
            )
        }
    }
}

@Preview()
@Composable
private fun Preview() {
    MoviesAppTheme {
        MoviePoster(
            title = "Movie Title",
            imageUrl = "https://example.com/movie-poster.jpg",
            onPosterClick = {}
        )
    }
}
