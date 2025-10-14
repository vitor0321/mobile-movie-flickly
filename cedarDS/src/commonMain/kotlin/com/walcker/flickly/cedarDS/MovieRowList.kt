package com.walcker.flickly.cedarDS

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.collections.immutable.ImmutableList

@Composable
public fun <T> MovieRowList(
    items: ImmutableList<T>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(horizontal = 16.dp),
    horizontalArrangement: Arrangement.Horizontal = Arrangement.spacedBy(16.dp),
    itemWidthFraction: Float = 0.65f,
    itemContent: @Composable (item: T, width: Float) -> Unit
) {
    BoxWithConstraints(
        modifier = modifier.fillMaxWidth()
    ) {
        val itemWidth = this.maxWidth * itemWidthFraction
        LazyRow(
            contentPadding = contentPadding,
            horizontalArrangement = horizontalArrangement
        ) {
            items(items.size) { index ->
                itemContent(items[index], itemWidth.value)
            }
        }
    }
}