package com.walcker.flickly.products.audio.features.ui.chapter

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.koin.koinScreenModel
import com.walcker.flickly.cedarDS.CedarLoadingContent
import com.walcker.flickly.cedarDS.CedarTopAppBar
import com.walcker.flickly.core.ui.step.Step
import com.walcker.flickly.core.ui.theme.MoviesAppTheme
import com.walcker.flickly.products.audio.features.domain.model.AudioBook
import com.walcker.flickly.products.audio.features.domain.model.BookTab
import com.walcker.flickly.products.audio.features.domain.model.Language
import com.walcker.flickly.products.audio.features.ui.chapter.components.AudioMediaPlayer
import com.walcker.flickly.products.audio.features.ui.chapter.components.ChaptersSelection
import com.walcker.flickly.products.audio.strings.BibleBooksStrings
import com.walcker.flickly.products.audio.strings.ChapterStrings
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.ArrowLeft
import compose.icons.fontawesomeicons.solid.AudioDescription
import compose.icons.fontawesomeicons.solid.BookOpen
import compose.icons.fontawesomeicons.solid.FileAudio

internal data class ChapterStep(
    private val language: Language,
    private val book: AudioBook,
    private val testament: BookTab,
) : Step() {

    @Composable
    override fun Content() {
        val model = koinScreenModel<ChapterStepModel>()
        val state by model.state.collectAsState()

        LaunchedEffect(model, language, book) {
            model.onEvent(ChapterInternalRoute.OnLoad(language, book, testament))
        }

        ChapterStepEvents(
            model = model,
            onEvent = model::onEvent,
            content = {
                ChapterStepContent(
                    state = state,
                    onEvent = model::onEvent,
                )
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun ChapterStepContent(
    state: ChapterState,
    onEvent: (ChapterInternalRoute) -> Unit,
) {
    Scaffold(
        topBar = {
            CedarTopAppBar(
                title = state.selectedBook.folderName(state.bibleBooksStrings),
                description = state.strings.selectAChapterTo,
                icon = FontAwesomeIcons.Solid.ArrowLeft,
                iconContent = FontAwesomeIcons.Solid.FileAudio,
                onNavigationBack = { onEvent(ChapterInternalRoute.OnPopBackStack) },
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            AudioMediaPlayer(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp),
                audioUrl = state.audioUrl,
            )
            HorizontalDivider()
            if (state.loading) {
                CedarLoadingContent()
            } else {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()),
                ) {
                    ChaptersSelection(
                        state = state,
                        onSelectChapter = { onEvent(ChapterInternalRoute.OnSelectChapter(it)) },
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun ChapterStepContentPreview() {
    MoviesAppTheme {
        ChapterStepContent(
            state = ChapterState(
                strings = ChapterStrings(
                    selectAChapterTo = "Select a chapter to listen",
                    chaptersAvailable = { "$it chapters available" },
                    oldTestamentLabel = "OT",
                    newTestamentLabel = "NT",
                ),
                bibleBooksStrings = BibleBooksStrings(),
                testament = BookTab.OldTestament,
                selectedChapter = 3,
                audioUrl = null,
            ),
            onEvent = {},
        )
    }
}
