package com.walcker.flickly.products.audio.features.ui.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedFilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.walcker.flickly.core.ui.theme.MoviesAppTheme
import com.walcker.flickly.products.audio.features.domain.model.Language
import com.walcker.flickly.products.audio.strings.HomeAudioStrings
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
internal fun LanguageSelection(
    selectedLanguage: Language,
    strings: HomeAudioStrings,
    languages: ImmutableList<Language>,
    onSelectLanguage: (Language) -> Unit,
) {

    Column(
        modifier = Modifier.padding(horizontal = 16.dp)
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            text = strings.language,
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onSurface,
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            items(languages) { languages ->
                val selected = languages == selectedLanguage

                ElevatedFilterChip(
                    onClick = { onSelectLanguage(languages) },
                    modifier = Modifier.height(36.dp),
                    shape = RoundedCornerShape(50),
                    elevation = FilterChipDefaults.elevatedFilterChipElevation(
                        elevation = if (selected) 2.dp else 0.dp,
                        pressedElevation = 1.dp,
                        focusedElevation = if (selected) 2.dp else 0.dp,
                        hoveredElevation = if (selected) 2.dp else 0.dp,
                    ),
                    label = {
                        Text(
                            text = languages.namePresentation(strings),
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
                        )
                    },
                    colors = FilterChipDefaults.elevatedFilterChipColors(
                        selectedContainerColor = MaterialTheme.colorScheme.onSurface,
                        selectedLabelColor = MaterialTheme.colorScheme.surface,
                        containerColor = Color.Transparent,
                        labelColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    ),
                    border = FilterChipDefaults.filterChipBorder(
                        enabled = true,
                        selected = selected,
                        borderColor = MaterialTheme.colorScheme.outline.copy(alpha = 0.4f),
                        selectedBorderColor = Color.Transparent,
                        borderWidth = 1.dp,
                        selectedBorderWidth = 0.dp,
                    ),
                    selected = selected,
                )
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    MoviesAppTheme {
        LanguageSelection(
            selectedLanguage = Language.URDU,
            languages = persistentListOf(Language.URDU, Language.PUNJABI_IN, Language.ENGLISH),
            strings = HomeAudioStrings(),
            onSelectLanguage = {},
        )
    }
}