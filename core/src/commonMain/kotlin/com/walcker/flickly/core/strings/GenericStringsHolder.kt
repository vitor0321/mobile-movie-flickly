package com.walcker.flickly.core.strings

public open class GenericStringsHolder<T : Any> {
    private var _strings: T? = null

    public val strings: T
        get() = _strings ?: error("Strings not initialized. Make sure to call setStrings() first.")

    public fun setStrings(strings: T) {
        _strings = strings
    }

    public fun clearStrings() {
        _strings = null
    }

    public fun hasStrings(): Boolean = _strings != null
}

public object Locales {
    const val EN = "en"
    const val PT = "pt"
    const val UR = "ur"
}