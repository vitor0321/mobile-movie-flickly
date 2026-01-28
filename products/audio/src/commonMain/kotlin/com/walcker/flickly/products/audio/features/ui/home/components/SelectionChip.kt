package com.walcker.flickly.products.audio.features.ui.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.walcker.flickly.core.ui.theme.MoviesAppTheme

@Composable
internal fun SelectionChip(
    modifier: Modifier = Modifier,
    text: String,
    textStyle: TextStyle,
    selected: Boolean,
    onClick: () -> Unit
) {
    AssistChip(
        modifier = modifier,
        onClick = onClick,
        shape = RoundedCornerShape(16.dp),
        label = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = text,
                style = textStyle,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.9f),
            )
        },
        colors = AssistChipDefaults.assistChipColors(
            containerColor = if (selected) MaterialTheme.colorScheme.secondaryContainer
            else MaterialTheme.colorScheme.surfaceContainer,
            labelColor = if (selected) MaterialTheme.colorScheme.onSecondaryContainer
            else MaterialTheme.colorScheme.onSurface,
        ),
        border = BorderStroke(
            width = 1.dp,
            color = if (selected) MaterialTheme.colorScheme.tertiary
            else MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun SelectionChipSelectedPreview() {
    MoviesAppTheme {
        SelectionChip(
            text = "1",
            selected = true,
            textStyle = MaterialTheme.typography.headlineSmall,
            onClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SelectionChipUnselectedPreview() {
    MoviesAppTheme {
        SelectionChip(
            text = "Gen",
            selected = false,
            textStyle = MaterialTheme.typography.headlineLarge,
            onClick = {}
        )
    }
}
