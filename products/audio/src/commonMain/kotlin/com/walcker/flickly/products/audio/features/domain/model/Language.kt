package com.walcker.flickly.products.audio.features.domain.model

import com.walcker.flickly.products.audio.strings.HomeAudioStrings

internal enum class Language(
    val folderName: String,
    val namePresentation: (HomeAudioStrings) -> String,
) {
    URDU(
        folderName = "urdu",
        namePresentation = { it.urName },
    ),
    PUNJABI(
        folderName = "punjabi",
        namePresentation = { it.paName },
    ),
    SINDHI(
        folderName = "sindhi",
        namePresentation = { it.sdName },
    ),
    ENGLISH(
        folderName = "english",
        namePresentation = { it.enName },
    ),
    PORTUGUESE(
        folderName = "portuguese",
        namePresentation = { it.ptName },
    ),
}