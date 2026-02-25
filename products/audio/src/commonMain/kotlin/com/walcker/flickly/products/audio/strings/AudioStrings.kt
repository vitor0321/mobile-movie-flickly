package com.walcker.flickly.products.audio.strings

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.key
import androidx.compose.runtime.remember
import androidx.compose.runtime.staticCompositionLocalOf
import cafe.adriel.lyricist.Lyricist
import cafe.adriel.lyricist.LyricistStrings
import com.walcker.flickly.core.strings.GenericStringsHolder
import com.walcker.flickly.core.strings.Locales
import com.walcker.flickly.core.strings.createLyricist

internal data class AudioStrings(
    val homeAudioStrings: HomeAudioStrings,
    val bibleBooksStrings: BibleBooksStrings,
    val chapterStrings: ChapterStrings,
)

@LyricistStrings(languageTag = Locales.EN, default = true)
internal val EnAudioStrings = AudioStrings(
    homeAudioStrings = homeAudioStringsEn,
    bibleBooksStrings = bibleBooksStringsEn,
    chapterStrings = chapterStringsEn,
)

@LyricistStrings(languageTag = Locales.PT)
internal val PtAudioStrings = AudioStrings(
    homeAudioStrings = homeAudioStringsPt,
    bibleBooksStrings = bibleBooksStringsPt,
    chapterStrings = chapterStringsPt,
)

@LyricistStrings(languageTag = Locales.UR)
internal val UrAudioStrings = AudioStrings(
    homeAudioStrings = homeAudioStringsUr,
    bibleBooksStrings = bibleBooksStringsUr,
    chapterStrings = chapterStringsUr,
)

@LyricistStrings(languageTag = Locales.PA_IN)
internal val PaInAudioStrings = AudioStrings(
    homeAudioStrings = homeAudioStringsPaIn,
    bibleBooksStrings = bibleBooksStringsPaIN,
    chapterStrings = chapterStringsPaIN,
)

@LyricistStrings(languageTag = Locales.PA_PK)
internal val PaPKAudioStrings = AudioStrings(
    homeAudioStrings = homeAudioStringsPaPK,
    bibleBooksStrings = bibleBooksStringsPaPK,
    chapterStrings = chapterStringsPaPK,
)

internal val LocalAudioStrings = staticCompositionLocalOf { EnAudioStrings }

internal class AudioStringsHolder : GenericStringsHolder<AudioStrings>()

@Composable
internal fun ProvideAudioStrings(
    lyricist: Lyricist<AudioStrings>,
    content: @Composable () -> Unit
) {
    key(lyricist.languageTag) {
        CompositionLocalProvider(LocalAudioStrings provides lyricist.strings) {
            content()
        }
    }
}

@Composable
internal fun rememberAudioStrings(
    languageTag: String,
): Lyricist<AudioStrings> {
    val lyricist = remember {
        createLyricist(
            defaultLanguageTag = languageTag,
            translations = mapOf(
                Locales.EN to EnAudioStrings,
                Locales.PT to PtAudioStrings,
                Locales.UR to UrAudioStrings,
                Locales.PA_IN to PaInAudioStrings,
                Locales.PA_PK to PaPKAudioStrings,
            ),
        )
    }

    LaunchedEffect(languageTag) {
        if (lyricist.languageTag != languageTag) {
            lyricist.languageTag = languageTag
        }
    }

    return lyricist
}

