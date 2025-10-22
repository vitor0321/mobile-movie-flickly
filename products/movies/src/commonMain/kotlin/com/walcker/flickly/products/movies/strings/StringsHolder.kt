package com.walcker.flickly.products.movies.strings

/**
 * Singleton holder for the current Strings instance.
 * This allows ViewModels and other classes to access strings without needing CompositionLocal.
 */
class StringsHolder {
    private var _strings: MoviesStrings? = null

    val strings: MoviesStrings
        get() = _strings ?: error("Strings not initialized. Make sure to call setStrings() first.")

    fun setStrings(strings: MoviesStrings) {
        _strings = strings
    }

    fun clearStrings() {
        _strings = null
    }

    fun hasStrings(): Boolean = _strings != null
}

