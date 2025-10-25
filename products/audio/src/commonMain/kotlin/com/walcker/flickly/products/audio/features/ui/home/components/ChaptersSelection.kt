package com.walcker.flickly.products.audio.features.ui.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.walcker.flickly.products.audio.features.domain.model.AudioBook
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList

@Composable
internal fun ChaptersSelection(
    selectedBook: AudioBook,
    selectedChapter: Int,
    availableChapters: ImmutableList<Int>,
    onSelectChapter: (Int) -> Unit,
) {
    Column {
        val chapters: ImmutableList<Int> = remember(availableChapters) {
            availableChapters.ifEmpty { (1..selectedBook.totalChapters).toImmutableList() }
        }

        FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            chapters.forEach { chapter ->
                val selected = remember(chapter) { chapter == selectedChapter }
                AssistChip(
                    onClick = { if (availableChapters.isEmpty() || chapter in availableChapters) onSelectChapter(chapter) },
                    label = {
                        Text(
                            text = chapter.toString(),
                            style = MaterialTheme.typography.headlineLarge,
                            color = MaterialTheme.colorScheme.onBackground,
                            fontWeight = FontWeight.Bold
                        )
                    },
                    colors = AssistChipDefaults.assistChipColors(
                        containerColor = if (selected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surfaceVariant,
                        labelColor = if (selected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurfaceVariant
                    )
                )
            }
        }
    }
}