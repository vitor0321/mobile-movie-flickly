package com.walcker.flickly.products.audio.features.ui.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.walcker.flickly.products.audio.features.domain.model.AudioBook
import com.walcker.flickly.products.audio.strings.AudioHomeStrings
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.ArrowDown
import compose.icons.fontawesomeicons.solid.ArrowUp
import kotlinx.collections.immutable.ImmutableList

@Composable
internal fun BookSelection(
    selectedBook: AudioBook,
    strings: AudioHomeStrings,
    availableBooks: ImmutableList<AudioBook>,
    onSelectBook: (AudioBook) -> Unit,
) {
    var showBooks by remember { mutableStateOf(false) }

    Column(Modifier.fillMaxWidth()) {
        Card(
            modifier = Modifier
                .padding(bottom = 8.dp)
                .clickable(enabled = availableBooks.isNotEmpty()) { showBooks = !showBooks }
                .fillMaxWidth(),
            shape = MaterialTheme.shapes.medium,
            elevation = CardDefaults.cardElevation(defaultElevation = 2.dp),
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 6.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = strings.book(selectedBook.name),
                    style = MaterialTheme.typography.headlineMedium,
                    color = MaterialTheme.colorScheme.onBackground,
                    fontFamily = FontFamily.SansSerif,
                    fontWeight = FontWeight.Bold
                )
                Icon(
                    modifier = Modifier.size(32.dp),
                    imageVector = if (showBooks) FontAwesomeIcons.Solid.ArrowUp else FontAwesomeIcons.Solid.ArrowDown,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onBackground
                )
            }
        }

        if (showBooks) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colorScheme.surfaceVariant, MaterialTheme.shapes.medium)
                    .padding(8.dp)
            ) {
                val books = availableBooks.ifEmpty { listOf(selectedBook) }
                LazyColumn(modifier = Modifier.heightIn(max = 300.dp)) {
                    items(books) { book ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    onSelectBook(book)
                                    showBooks = false
                                }
                                .padding(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = book.name,
                                style = MaterialTheme.typography.bodyMedium,
                                color = if (book == selectedBook) MaterialTheme.colorScheme.onSurfaceVariant else MaterialTheme.colorScheme.outline,
                                fontFamily = FontFamily.Serif,
                                fontWeight = FontWeight.Light
                            )
                        }
                    }
                }
            }
        }
    }
}