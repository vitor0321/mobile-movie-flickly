package com.walcker.flickly.products.audio.features.ui.home.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.CircleShape
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
        shape = CircleShape,
        label = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = text,
                style = textStyle,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center
            )
        },
        colors = AssistChipDefaults.assistChipColors(
            containerColor = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
            labelColor = if (selected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant
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
