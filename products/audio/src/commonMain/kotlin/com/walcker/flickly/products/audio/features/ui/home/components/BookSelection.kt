package com.walcker.flickly.products.audio.features.ui.home.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import kotlinx.collections.immutable.toImmutableList

@Composable
internal fun BookSelection(
    selectedBook: AudioBook,
    strings: AudioHomeStrings,
    bibleBooksStrings: BibleBooksStrings,
    availableBooks: ImmutableList<AudioBook>,
    onSelectBook: (AudioBook) -> Unit,
) {
    Column {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            text = strings.books,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
        )
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            val books = availableBooks.ifEmpty { AudioBook.entries.toImmutableList() }
            items(books) { book ->
                val selected = book == selectedBook
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    SelectionChip(
                        modifier = Modifier.size(80.dp),
                        text = book.folderName(bibleBooksStrings).take(3),
                        textStyle = MaterialTheme.typography.headlineLarge,
                        selected = selected,
                        onClick = { onSelectBook(book) }
                    )
                    Text(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 6.dp),
                        text = book.folderName(bibleBooksStrings),
                        style = MaterialTheme.typography.bodyMedium,
                        fontWeight = FontWeight.Bold,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun BookSelectionPreview() {
    MoviesAppTheme {
        BookSelection(
            selectedBook = AudioBook.Genesis,
            availableBooks = AudioBook.entries.toImmutableList(),
            strings = AudioHomeStrings(),
            bibleBooksStrings = BibleBooksStrings(),
            onSelectBook = {}
        )
    }
}