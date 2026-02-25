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
    PUNJABI_IN(
        folderName = "punjabi_in",
        namePresentation = { it.paInName },
    ),
    PUNJABI_PK(
        folderName = "punjabi_pk",
        namePresentation = { it.paPkName },
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