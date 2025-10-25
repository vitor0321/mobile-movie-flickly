package com.walcker.flickly.products.audio.features.ui.home

import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.koin.koinScreenModel
import com.walcker.flickly.cedarDS.CedarLoadingContent
import com.walcker.flickly.cedarDS.CedarTopAppBar
import com.walcker.flickly.core.ui.step.Step
import com.walcker.flickly.products.audio.features.domain.model.AudioBook
import com.walcker.flickly.products.audio.features.ui.home.components.AudioMediaPlayer
import com.walcker.flickly.products.audio.features.ui.home.components.BookSelection
import com.walcker.flickly.products.audio.features.ui.home.components.ChaptersSelection
import com.walcker.flickly.products.audio.strings.AudioHomeStrings
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
                    strings = state.strings,
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
    strings: AudioHomeStrings,
    onEvent: (HomeAudioInternalRoute) -> Unit,
) {
    Scaffold(
        topBar = {
            CedarTopAppBar(
                title = strings.title,
                icon = FontAwesomeIcons.Solid.ArrowLeft,
                onNavigationBack = { onEvent(HomeAudioInternalRoute.OnPopBackStack) },
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(24.dp)
            ) {
                if (state.loading) {
                    CedarLoadingContent()
                } else {
                    BookAndChapterSelector(
                        strings = strings,
                        selectedBook = state.selectedBook,
                        selectedChapter = state.selectedChapter,
                        availableBooks = state.availableBooks,
                        availableChapters = state.availableChapters,
                        onSelectBook = { onEvent(HomeAudioInternalRoute.OnSelectBook(it)) },
                        onSelectChapter = { onEvent(HomeAudioInternalRoute.OnSelectChapter(it)) }
                    )

                    Spacer(modifier = Modifier.weight(1f))
                    state.audioUrl?.let {
                        AudioMediaPlayer(audioUrl = state.audioUrl)
                    }
                }
            }
        }
    }
}

@Composable
private fun BookAndChapterSelector(
    selectedBook: AudioBook,
    strings: AudioHomeStrings,
    selectedChapter: Int,
    availableBooks: ImmutableList<AudioBook>,
    availableChapters: ImmutableList<Int>,
    onSelectBook: (AudioBook) -> Unit,
    onSelectChapter: (Int) -> Unit,
) {
    Column(
        modifier = Modifier.scrollable(
            state = rememberScrollState(),
            orientation = Orientation.Vertical
        ),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.Start
    ) {
        BookSelection(
            selectedBook = selectedBook,
            strings = strings,
            availableBooks = availableBooks,
            onSelectBook = onSelectBook,
        )

        ChaptersSelection(
            selectedBook = selectedBook,
            selectedChapter = selectedChapter,
            availableChapters = availableChapters,
            onSelectChapter = onSelectChapter,
        )
    }
}