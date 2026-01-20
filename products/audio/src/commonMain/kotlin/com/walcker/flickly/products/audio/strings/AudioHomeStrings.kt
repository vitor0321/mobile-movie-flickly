package com.walcker.flickly.products.audio.strings

internal data class AudioHomeStrings(
    val chapter: (String) -> String = { "" },
    val peace: String = "",
    val books: String = "",
)

internal val audioHomeStringsPt = AudioHomeStrings(
    chapter = { "Capítulo $it" },
    peace = "Paz seja convosco",
    books = "Livros",
)

internal val audioHomeStringsEn = AudioHomeStrings(
    chapter = { "Chapter $it" },
    peace = "Peace be with you",
    books = "Books",
)

internal val audioHomeStringsUr = AudioHomeStrings(
    chapter = { "باب $it" },
    peace = "آپ پر سلامتی ہو",
    books = "کتابیں",
)