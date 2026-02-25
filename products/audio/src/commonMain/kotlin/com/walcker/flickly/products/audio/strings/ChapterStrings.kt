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
    oldTestamentLabel = "Antigo Testamento",
    newTestamentLabel = "Novo Testamento",
)

internal val chapterStringsEn = ChapterStrings(
    selectAChapterTo = "Select a chapter to listen",
    chaptersAvailable = { "$it chapters available" },
    oldTestamentLabel = "Old Testament",
    newTestamentLabel = "New Testament",
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
    oldTestamentLabel = "عہدِ عتیق",
    newTestamentLabel = "عہدِ جدید",
)

internal val chapterStringsPaPK = ChapterStrings(
    selectAChapterTo = "سنن لئی اک باب منتخب کرو",
    chaptersAvailable = { "$it باب دستیاب نیں" },
    oldTestamentLabel = "پرانا عہدنامہ",
    newTestamentLabel = "نواں عہدنامہ",
)

internal val chapterStringsSdPk = ChapterStrings(
    selectAChapterTo = "ٻڌڻ لاءِ هڪ باب چونڊيو",
    chaptersAvailable = { "$it باب موجود آهن" },
    oldTestamentLabel = "پراڻو عهد نامو",
    newTestamentLabel = "نئون عهد نامو",
)

internal val chapterStringsSdIn = ChapterStrings(
    selectAChapterTo = "सुनने के लिए एक अध्याय चुनें",
    chaptersAvailable = { "$it अध्याय उपलब्ध हैं" },
    oldTestamentLabel = "पुरानो नियम",
    newTestamentLabel = "नवो नियम",
)