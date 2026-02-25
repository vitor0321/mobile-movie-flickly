package com.walcker.flickly.products.audio.features.ui.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
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
import com.walcker.flickly.products.audio.strings.HomeAudioStrings
import com.walcker.flickly.products.audio.strings.BibleBooksStrings
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList

@Composable
internal fun BookSelection(
    selectedBook: AudioBook,
    selectedTab: BookTab,
    tabs: ImmutableList<BookTab>,
    strings: HomeAudioStrings,
    bibleBooksStrings: BibleBooksStrings,
    availableNewTestamentBooks: ImmutableList<AudioBook>,
    availableOldTestamentBooks: ImmutableList<AudioBook>,
    onSelectBook: (AudioBook) -> Unit,
    onSelectTab: (BookTab) -> Unit,
) {
    val hasOT = remember { availableOldTestamentBooks.isNotEmpty() }
    val hasNT = remember { availableNewTestamentBooks.isNotEmpty() }


    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
        ) {
            tabs.forEach { tab ->
                val isSelected = tab == selectedTab
                Surface(
                    modifier = Modifier
                        .clip(RoundedCornerShape(50))
                        .clickable { onSelectTab(tab) },
                    shape = RoundedCornerShape(50),
                    color = if (isSelected) MaterialTheme.colorScheme.onSurface else Color.Transparent,
                    border = if (!isSelected) BorderStroke(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.outline.copy(alpha = 0.4f),
                    ) else null,
                ) {
                    Text(
                        text = tab.label(strings),
                        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                        color = if (isSelected) MaterialTheme.colorScheme.surface
                        else MaterialTheme.colorScheme.onSurfaceVariant,
                    )
                }
            }
        }

        when (selectedTab) {
            BookTab.All -> {
                if (hasOT) TestamentSection(
                    title = strings.oldTestament,
                    bookCount = availableOldTestamentBooks.size,
                    books = availableOldTestamentBooks,
                    selectedBook = selectedBook,
                    bibleBooksStrings = bibleBooksStrings,
                    accentColor = MaterialTheme.colorScheme.tertiaryFixed,
                    strings = strings,
                    onSelectBook = onSelectBook,
                )
                if (hasNT) TestamentSection(
                    title = strings.newTestament,
                    bookCount = availableNewTestamentBooks.size,
                    books = availableNewTestamentBooks,
                    selectedBook = selectedBook,
                    bibleBooksStrings = bibleBooksStrings,
                    accentColor = MaterialTheme.colorScheme.tertiaryFixedDim,
                    strings = strings,
                    onSelectBook = onSelectBook,
                )
            }

            BookTab.OldTestament -> {
                if (hasOT) TestamentSection(
                    title = strings.oldTestament,
                    bookCount = availableOldTestamentBooks.size,
                    books = availableOldTestamentBooks,
                    selectedBook = selectedBook,
                    bibleBooksStrings = bibleBooksStrings,
                    accentColor = MaterialTheme.colorScheme.tertiaryFixed,
                    strings = strings,
                    onSelectBook = onSelectBook,
                )
            }

            BookTab.NewTestament -> {
                if (hasNT) TestamentSection(
                    title = strings.newTestament,
                    bookCount = availableNewTestamentBooks.size,
                    books = availableNewTestamentBooks,
                    selectedBook = selectedBook,
                    bibleBooksStrings = bibleBooksStrings,
                    accentColor = MaterialTheme.colorScheme.tertiaryFixedDim,
                    strings = strings,
                    onSelectBook = onSelectBook,
                )
            }
        }
    }
}

@Composable
private fun TestamentSection(
    title: String,
    bookCount: Int,
    books: ImmutableList<AudioBook>,
    selectedBook: AudioBook,
    bibleBooksStrings: BibleBooksStrings,
    accentColor: Color,
    strings: HomeAudioStrings,
    onSelectBook: (AudioBook) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 16.dp, bottom = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Box(
            modifier = Modifier
                .size(10.dp)
                .clip(CircleShape)
                .background(accentColor)
        )
        Text(
            text = title,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onSurface,
        )
        Text(
            text = "($bookCount ${strings.books})",
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
        )
    }

    books.forEach { book ->
        BookRow(
            book = book,
            bibleBooksStrings = bibleBooksStrings,
            isSelected = book == selectedBook,
            accentColor = accentColor,
            chaptersLabel = strings.chaptersCount,
            onSelectBook = onSelectBook,
        )
    }
}

@Composable
private fun BookRow(
    book: AudioBook,
    bibleBooksStrings: BibleBooksStrings,
    isSelected: Boolean,
    accentColor: Color,
    chaptersLabel: (Int) -> String,
    onSelectBook: (AudioBook) -> Unit,
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .clickable { onSelectBook(book) },
        shape = RoundedCornerShape(12.dp),
        color = if (isSelected) MaterialTheme.colorScheme.surfaceContainerHigh
        else MaterialTheme.colorScheme.surfaceContainerLow,
        tonalElevation = if (isSelected) 4.dp else 0.dp,
        border = if (isSelected) BorderStroke(1.dp, accentColor.copy(alpha = 0.4f)) else null,
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 14.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = book.folderName(bibleBooksStrings),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onSurface,
                )
                Text(
                    text = chaptersLabel(book.totalChapters),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(accentColor),
                contentAlignment = Alignment.Center,
            ) {
                Text(
                    text = book.totalChapters.toString(),
                    style = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                )
            }
        }
    }
}

@Preview
@Composable
private fun BookSelectionPreview() {
    MoviesAppTheme {
        BookSelection(
            selectedBook = AudioBook.Genesis,
            selectedTab = BookTab.All,
            tabs = persistentListOf(BookTab.All, BookTab.OldTestament, BookTab.NewTestament),
            availableOldTestamentBooks = AudioBook.entries.take(6).toImmutableList(),
            availableNewTestamentBooks = AudioBook.entries.takeLast(6).toImmutableList(),
            strings = HomeAudioStrings(
                oldTestament = "Old Testament",
                newTestament = "New Testament",
                allBooks = "All Books",
                books = "Books",
                chaptersCount = { "$it chapters" },
            ),
            bibleBooksStrings = BibleBooksStrings(),
            onSelectBook = {},
            onSelectTab = {},
        )
    }
}