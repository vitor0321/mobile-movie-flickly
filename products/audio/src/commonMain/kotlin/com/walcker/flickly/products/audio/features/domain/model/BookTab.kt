package com.walcker.flickly.products.audio.features.domain.model

import com.walcker.flickly.products.audio.strings.HomeAudioStrings

internal enum class BookTab(
    val label: (HomeAudioStrings) -> String
) {
    All(label = { it.allBooks }),
    OldTestament(label = { it.oldTestament }),
    NewTestament(label = { it.newTestament }), ;
}