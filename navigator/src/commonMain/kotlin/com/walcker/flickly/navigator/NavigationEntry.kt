package com.walcker.flickly.navigator

import cafe.adriel.voyager.core.screen.Screen

public interface MoviesDestination {
    fun moviesHome(): Screen
    fun movieDetails(movieId: String): Screen
}

public interface AudioDestination {
    fun audioEntryPoint(): Screen
    fun audioHome(): Screen
}