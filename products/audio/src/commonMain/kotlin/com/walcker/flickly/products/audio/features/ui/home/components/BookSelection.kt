package com.walcker.flickly.products.audio.features.ui.home.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AssistChipDefaults
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    availableNewTestamentBooks: ImmutableList<AudioBook>,
    availableOldTestamentBooks: ImmutableList<AudioBook>,
    onSelectBook: (AudioBook) -> Unit,
) {
    val hasOldTestamentBooks = remember(availableOldTestamentBooks) { availableOldTestamentBooks.isNotEmpty() }
    val hasNewTestamentBooks = remember(availableNewTestamentBooks) { availableNewTestamentBooks.isNotEmpty() }

    val typography = MaterialTheme.typography
    val headerStyle = remember(typography) {
        typography.headlineSmall.copy(
            fontWeight = FontWeight.SemiBold,
            letterSpacing = (-0.2).sp,
        )
    }
    val sectionStyle = remember(typography) {
        typography.titleMedium.copy(
            fontWeight = FontWeight.SemiBold,
            letterSpacing = 0.1.sp,
        )
    }

    Column {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp),
            text = strings.books,
            style = headerStyle,
            color = MaterialTheme.colorScheme.onSurface,
        )

        if (hasOldTestamentBooks) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp, bottom = 10.dp),
                text = strings.oldTestament,
                style = sectionStyle,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.9f),
            )
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(availableOldTestamentBooks) { book ->
                    val selected = book == selectedBook

                    ElevatedAssistChip(
                        onClick = { onSelectBook(book) },
                        modifier = Modifier
                            .width(140.dp)
                            .height(56.dp),
                        shape = RoundedCornerShape(16.dp),
                        elevation = AssistChipDefaults.elevatedAssistChipElevation(
                            elevation = if (selected) 3.dp else 0.dp,
                            pressedElevation = 1.dp,
                            focusedElevation = if (selected) 4.dp else 1.dp,
                            hoveredElevation = if (selected) 4.dp else 1.dp,
                        ),
                        label = {
                            Text(
                                text = book.folderName(bibleBooksStrings),
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = if (selected) FontWeight.Bold else FontWeight.SemiBold,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth(),
                            )
                        },
                        colors = AssistChipDefaults.elevatedAssistChipColors(
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
            }
        }

        if (hasNewTestamentBooks) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp, bottom = 10.dp),
                text = strings.newTestament,
                style = sectionStyle,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.9f),
            )
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                items(availableNewTestamentBooks) { book ->
                    val selected = book == selectedBook

                    ElevatedAssistChip(
                        onClick = { onSelectBook(book) },
                        modifier = Modifier
                            .width(140.dp)
                            .height(56.dp),
                        shape = RoundedCornerShape(16.dp),
                        elevation = AssistChipDefaults.elevatedAssistChipElevation(
                            elevation = if (selected) 3.dp else 0.dp,
                            pressedElevation = 1.dp,
                            focusedElevation = if (selected) 4.dp else 1.dp,
                            hoveredElevation = if (selected) 4.dp else 1.dp,
                        ),
                        label = {
                            Text(
                                text = book.folderName(bibleBooksStrings),
                                style = MaterialTheme.typography.titleMedium,
                                fontWeight = if (selected) FontWeight.Bold else FontWeight.SemiBold,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth(),
                            )
                        },
                        colors = AssistChipDefaults.elevatedAssistChipColors(
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
            availableOldTestamentBooks = AudioBook.entries.toImmutableList(),
            availableNewTestamentBooks = AudioBook.entries.toImmutableList(),
            strings = AudioHomeStrings(),
            bibleBooksStrings = BibleBooksStrings(),
            onSelectBook = {}
        )
    }
}