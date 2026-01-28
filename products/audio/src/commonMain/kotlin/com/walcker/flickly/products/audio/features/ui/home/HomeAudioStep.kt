package com.walcker.flickly.products.audio.features.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.koin.koinScreenModel
import com.walcker.flickly.cedarDS.CedarLoadingContent
import com.walcker.flickly.cedarDS.CedarTopAppBar
import com.walcker.flickly.core.ui.step.Step
import com.walcker.flickly.core.ui.theme.MoviesAppTheme
import com.walcker.flickly.products.audio.features.domain.model.AudioBook
import com.walcker.flickly.products.audio.features.ui.home.components.AudioMediaPlayer
import com.walcker.flickly.products.audio.features.ui.home.components.BookSelection
import com.walcker.flickly.products.audio.features.ui.home.components.ChaptersSelection
import com.walcker.flickly.products.audio.features.ui.preview.HomeAudioStatePreviewProvider
import com.walcker.flickly.products.audio.strings.AudioHomeStrings
import com.walcker.flickly.products.audio.strings.BibleBooksStrings
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.ArrowLeft
import kotlinx.collections.immutable.ImmutableList

internal data object HomeAudioStep : Step() {

    @Composable
    override fun Content() {
        val model = koinScreenModel<HomeAudioStepModel>()
        val state by model.state.collectAsState()

        HomeMoviesStepEvents(
            model = model,
            onEvent = model::onEvent,
            content = {
                HomeAudioStepContent(
                    state = state,
                    bibleBooksStrings = state.bibleBooksStrings,
                    onEvent = model::onEvent,
                )
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeAudioStepContent(
    state: HomeAudioState,
    bibleBooksStrings: BibleBooksStrings,
    onEvent: (HomeAudioInternalRoute) -> Unit,
) {
    Scaffold(
        topBar = {
            CedarTopAppBar(
                icon = FontAwesomeIcons.Solid.ArrowLeft,
                onNavigationBack = { onEvent(HomeAudioInternalRoute.OnPopBackStack) },
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            if (state.loading) {
                CedarLoadingContent()
            } else {
                BookAndChapterSelector(
                    selectedBook = state.selectedBook,
                    strings = state.strings,
                    bibleBooksStrings = bibleBooksStrings,
                    selectedChapter = state.selectedChapter,
                    audioUrl = state.audioUrl,
                    availableNewTestamentBooks = state.availableNewTestamentBooks,
                    availableOldTestamentBooks = state.availableOldTestamentBooks,
                    availableChapters = state.availableChapters,
                    onSelectBook = { onEvent(HomeAudioInternalRoute.OnSelectBook(it)) },
                    onSelectChapter = { onEvent(HomeAudioInternalRoute.OnSelectChapter(it)) }
                )
            }
        }
    }
}

@Composable
private fun BookAndChapterSelector(
    selectedBook: AudioBook,
    strings: AudioHomeStrings,
    bibleBooksStrings: BibleBooksStrings,
    audioUrl: String?,
    selectedChapter: Int,
    availableNewTestamentBooks: ImmutableList<AudioBook>,
    availableOldTestamentBooks: ImmutableList<AudioBook>,
    availableChapters: ImmutableList<Int>,
    onSelectBook: (AudioBook) -> Unit,
    onSelectChapter: (Int) -> Unit,
) {
    Column {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp),
        ) {
            Text(
                modifier = Modifier
                    .padding(bottom = 8.dp)
                    .fillMaxWidth(),
                text = strings.peace,
                style = MaterialTheme.typography.headlineMedium.copy(
                    fontWeight = FontWeight.Bold,
                    letterSpacing = (-0.2).sp,
                ),
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onSurface,
            )
            BookSelection(
                selectedBook = selectedBook,
                strings = strings,
                bibleBooksStrings = bibleBooksStrings,
                availableNewTestamentBooks = availableNewTestamentBooks,
                availableOldTestamentBooks = availableOldTestamentBooks,
                onSelectBook = onSelectBook,
            )
        }
        HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))
        AudioMediaPlayer(audioUrl = audioUrl)
        HorizontalDivider(modifier = Modifier.padding(vertical = 16.dp))
        ChaptersSelection(
            selectedBook = selectedBook,
            strings = strings,
            bibleBooksStrings = bibleBooksStrings,
            selectedChapter = selectedChapter,
            availableChapters = availableChapters,
            onSelectChapter = onSelectChapter,
        )
        Spacer(modifier = Modifier.weight(1f))
    }
}

@Preview
@Composable
private fun HomeAudioStepContentPreview(
    @PreviewParameter(HomeAudioStatePreviewProvider::class) homeAudioState: HomeAudioState
) {
    MoviesAppTheme {
        HomeAudioStepContent(
            state = homeAudioState,
            bibleBooksStrings = BibleBooksStrings(),
            onEvent = {}
        )
    }
}