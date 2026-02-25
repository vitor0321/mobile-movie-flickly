package com.walcker.flickly.products.audio.features.ui.chapter.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
    accentColor: Color = MaterialTheme.colorScheme.tertiaryFixed,
    onClick: () -> Unit,
) {
    AssistChip(
        modifier = modifier,
        onClick = onClick,
        shape = RoundedCornerShape(12.dp),
        label = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = text,
                style = textStyle,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
            )
        },
        colors = AssistChipDefaults.assistChipColors(
            containerColor = if (selected) accentColor else MaterialTheme.colorScheme.surfaceContainer,
            labelColor = if (selected) Color.White else MaterialTheme.colorScheme.onSurfaceVariant,
        ),
        border = if (selected) null else BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.outline.copy(alpha = 0.4f),
        ),
    )
}

@Preview(showBackground = true)
@Composable
private fun SelectionChipSelectedPreview() {
    MoviesAppTheme {
        SelectionChip(
            text = "1",
            selected = true,
            accentColor = Color(0xFFE07B00),
            textStyle = MaterialTheme.typography.bodyLarge,
            onClick = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SelectionChipUnselectedPreview() {
    MoviesAppTheme {
        SelectionChip(
            text = "5",
            selected = false,
            textStyle = MaterialTheme.typography.bodyLarge,
            onClick = {}
        )
    }
}
