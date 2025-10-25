package com.walcker.flickly.core.navigation

import cafe.adriel.voyager.navigator.Navigator

/**
 * Singleton holder for the current Navigator instance.
 * This allows StepModels to access the Navigator without needing it passed as a parameter.
 */
class NavigatorHolder {
    private var _navigator: Navigator? = null

    val navigator: Navigator
        get() = _navigator ?: error("Navigator not initialized. Make sure to call setNavigator() first.")

    fun setNavigator(navigator: Navigator) {
        _navigator = navigator
    }

    fun clearNavigator() {
        _navigator = null
    }

    fun hasNavigator(): Boolean = _navigator != null
}