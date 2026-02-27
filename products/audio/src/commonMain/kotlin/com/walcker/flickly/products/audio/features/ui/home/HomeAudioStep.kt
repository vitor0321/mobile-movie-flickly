package com.walcker.flickly.products.audio.features.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.koin.koinNavigatorScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.walcker.flickly.cedarDS.CedarLoadingContent
import com.walcker.flickly.cedarDS.CedarTopAppBar
import com.walcker.flickly.core.ui.step.Step
import com.walcker.flickly.core.ui.theme.MoviesAppTheme
import com.walcker.flickly.products.audio.features.domain.model.AudioBook
import com.walcker.flickly.products.audio.features.domain.model.BookTab
import com.walcker.flickly.products.audio.features.ui.home.components.BookSelection
import com.walcker.flickly.products.audio.features.ui.home.components.LanguageSelection
import com.walcker.flickly.products.audio.features.ui.preview.HomeAudioStatePreviewProvider
import com.walcker.flickly.products.audio.strings.HomeAudioStrings
import com.walcker.flickly.products.audio.strings.BibleBooksStrings
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.ArrowLeft
import compose.icons.fontawesomeicons.solid.UserLock

internal data object HomeAudioStep : Step() {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.currentOrThrow
        val model = navigator.koinNavigatorScreenModel<HomeAudioStepModel>()
        val state by model.state.collectAsState()

        HomeMoviesStepEvents(
            model = model,
            strings = state.strings,
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
                title = state.strings.titleToolbar,
                description = state.strings.peace,
                icon = FontAwesomeIcons.Solid.ArrowLeft,
                iconContent = FontAwesomeIcons.Solid.UserLock,
                onNavigationBack = { onEvent(HomeAudioInternalRoute.OnPopBackStack) },
                onIconContentClick = { onEvent(HomeAudioInternalRoute.OnShowChangePassword) },
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
            LanguageSelection(
                selectedLanguage = state.selectedLanguage,
                strings = state.strings,
                languages = state.languages,
                onSelectLanguage = { onEvent(HomeAudioInternalRoute.OnSelectLanguage(it)) }
            )
            HorizontalDivider()
            if (state.loading) {
                CedarLoadingContent()
            } else {
                BookAndChapterSelector(
                    state = state,
                    strings = state.strings,
                    bibleBooksStrings = bibleBooksStrings,
                    onSelectBook = { onEvent(HomeAudioInternalRoute.OnSelectBook(it)) },
                    onSelectTab = { onEvent(HomeAudioInternalRoute.OnSelectTab(it)) },
                )
            }
        }
    }
}

@Composable
private fun BookAndChapterSelector(
    state: HomeAudioState,
    strings: HomeAudioStrings,
    bibleBooksStrings: BibleBooksStrings,
    onSelectBook: (AudioBook) -> Unit,
    onSelectTab: (BookTab) -> Unit,
) {
    Column {
        BookSelection(
            selectedBook = state.selectedBook,
            selectedTab = state.selectedTab,
            tabs = state.tabs,
            strings = strings,
            bibleBooksStrings = bibleBooksStrings,
            availableNewTestamentBooks = state.availableNewTestamentBooks,
            availableOldTestamentBooks = state.availableOldTestamentBooks,
            onSelectBook = onSelectBook,
            onSelectTab = onSelectTab,
        )
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