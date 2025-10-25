package com.walcker.flickly.products.audio.strings

internal data class AudioHomeStrings(
    val title: String = "",
    val book: (String) -> String = { "" },
)

internal val audioHomeStringsPt = AudioHomeStrings(
    title = "Bíblia em Áudio",
    book = { "Livro $it" },
)

internal val audioHomeStringsEn = AudioHomeStrings(
    title = "Audio Bible",
    book = { "Book $it" },
)

internal val audioHomeStringsUr = AudioHomeStrings(
    title = "آڈیو بائبل",
    book = { "کتاب $it" },
)