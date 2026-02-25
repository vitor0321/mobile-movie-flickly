package com.walcker.flickly.products.audio.strings

internal data class ChapterStrings(
    val selectAChapterTo: String = "",
    val chaptersAvailable: (Int) -> String = { "" },
    val oldTestamentLabel: String = "",
    val newTestamentLabel: String = "",
)

internal val chapterStringsPt = ChapterStrings(
    selectAChapterTo = "Selecione um capítulo para ouvir",
    chaptersAvailable = { "$it capítulos disponíveis" },
    oldTestamentLabel = "AT",
    newTestamentLabel = "NT",
)

internal val chapterStringsEn = ChapterStrings(
    selectAChapterTo = "Select a chapter to listen",
    chaptersAvailable = { "$it chapters available" },
    oldTestamentLabel = "OT",
    newTestamentLabel = "NT",
)

internal val chapterStringsUr = ChapterStrings(
    selectAChapterTo = "سننے کے لیے ایک باب منتخب کریں",
    chaptersAvailable = { "$it ابواب دستیاب ہیں" },
    oldTestamentLabel = "عہدِ عتیق",
    newTestamentLabel = "عہدِ جدید",
)

internal val chapterStringsPaIN = ChapterStrings(
    selectAChapterTo = "ਸੁਣਨ ਲਈ ਇੱਕ ਅਧਿਆਇ ਚੁਣੋ",
    chaptersAvailable = { "$it ਅਧਿਆਇ ਉਪਲਬਧ ਹਨ" },
    oldTestamentLabel = "ਪੁਰਾਣਾ ਨਿਯਮ",
    newTestamentLabel = "ਨਵਾਂ ਨਿਯਮ",
)

internal val chapterStringsPaPK = ChapterStrings(
    selectAChapterTo = "سنن لئی اک باب منتخب کرو",
    chaptersAvailable = { "$it باب دستیاب نیں" },
    oldTestamentLabel = "پرانا عہدنامہ",
    newTestamentLabel = "نواں عہدنامہ",
)