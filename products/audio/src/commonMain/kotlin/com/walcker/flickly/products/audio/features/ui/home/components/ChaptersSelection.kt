package com.walcker.flickly.products.audio.features.ui.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.walcker.flickly.core.ui.theme.MoviesAppTheme
import com.walcker.flickly.products.audio.features.domain.model.AudioBook
import com.walcker.flickly.products.audio.strings.AudioHomeStrings
import com.walcker.flickly.products.audio.strings.BibleBooksStrings
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList

@Composable
internal fun ChaptersSelection(
    selectedBook: AudioBook,
    strings: AudioHomeStrings,
    bibleBooksStrings: BibleBooksStrings,
    selectedChapter: Int,
    availableChapters: ImmutableList<Int>,
    onSelectChapter: (Int) -> Unit,
) {
    Column(
        modifier = Modifier
            .padding(horizontal = 16.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        val chapters: ImmutableList<Int> = remember(availableChapters) {
            availableChapters.ifEmpty { (1..selectedBook.totalChapters).toImmutableList() }
        }
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            text = strings.chapter(selectedBook.folderName(bibleBooksStrings)),
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
        )

        FlowRow(
            modifier = Modifier.fillMaxWidth(),
            maxItemsInEachRow = 5,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            chapters.forEach { chapter ->
                val selected = chapter == selectedChapter

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .fillMaxWidth()
                ) {
                    SelectionChip(
                        modifier = Modifier
                            .padding(bottom = 8.dp)
                            .align(Alignment.TopStart)
                            .size(56.dp),
                        text = chapter.toString(),
                        textStyle = MaterialTheme.typography.bodyLarge,
                        selected = selected,
                        onClick = { if (availableChapters.isEmpty() || chapter in availableChapters) onSelectChapter(chapter) }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ChaptersSelectionPreview() {
    MoviesAppTheme {
        ChaptersSelection(
            selectedBook = AudioBook.Genesis,
            strings = AudioHomeStrings(),
            bibleBooksStrings = BibleBooksStrings(),
            selectedChapter = 1,
            availableChapters = persistentListOf(),
            onSelectChapter = {}
        )
    }
}