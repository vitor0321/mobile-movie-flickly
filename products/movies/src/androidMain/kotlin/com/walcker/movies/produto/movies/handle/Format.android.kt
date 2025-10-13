package com.walcker.movies.produto.movies.handle

import java.util.Locale

actual fun Double.formatRating(): String =
    String
        .format(Locale.getDefault(), "%.1f", this)
        .replace(',', '.')