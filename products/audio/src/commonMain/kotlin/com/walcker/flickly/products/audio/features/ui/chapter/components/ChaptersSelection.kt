package com.walcker.flickly.products.audio.features.ui.chapter.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.walcker.flickly.core.ui.theme.MoviesAppTheme
import com.walcker.flickly.products.audio.features.domain.model.AudioBook
import com.walcker.flickly.products.audio.features.domain.model.BookTab
import com.walcker.flickly.products.audio.features.ui.chapter.ChapterState
import com.walcker.flickly.products.audio.strings.BibleBooksStrings
import com.walcker.flickly.products.audio.strings.ChapterStrings
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList

@Composable
internal fun ChaptersSelection(
    state: ChapterState,
    onSelectChapter: (Int) -> Unit,
) {
    val isOldTestament = remember { state.testament == BookTab.OldTestament }
    val accentColor = if (isOldTestament) MaterialTheme.colorScheme.tertiaryFixed
    else MaterialTheme.colorScheme.tertiaryFixedDim

    val chapters = remember(state.availableChapters) {
        state.availableChapters.ifEmpty { (1..state.selectedBook.totalChapters).toImmutableList() }
    }

    val testamentLabel = if (isOldTestament) state.strings.oldTestamentLabel
    else state.strings.newTestamentLabel

    Column(modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = state.strings.chaptersAvailable(chapters.size),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .background(accentColor),
            ) {
                Text(
                    text = testamentLabel,
                    modifier = Modifier.padding(horizontal = 12.dp, vertical = 4.dp),
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                )
            }
        }

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp, alignment = Alignment.CenterHorizontally),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            chapters.forEach { chapter ->
                SelectionChip(
                    modifier = Modifier.size(56.dp),
                    text = chapter.toString(),
                    textStyle = MaterialTheme.typography.bodyLarge,
                    selected = chapter == state.selectedChapter,
                    accentColor = accentColor,
                    onClick = {
                        if (state.availableChapters.isEmpty() || chapter in state.availableChapters) {
                            onSelectChapter(chapter)
                        }
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ChaptersSelectionPreview() {
    MoviesAppTheme {
        ChaptersSelection(
            state = ChapterState(
                selectedBook = AudioBook.Genesis,
                selectedChapter = 3,
                testament = BookTab.OldTestament,
                availableChapters = persistentListOf(),
                strings = ChapterStrings(
                    chaptersAvailable = { "$it chapters available" },
                    oldTestamentLabel = "OT",
                    newTestamentLabel = "NT",
                ),
                bibleBooksStrings = BibleBooksStrings(),
            ),
            onSelectChapter = {}
        )
    }
}