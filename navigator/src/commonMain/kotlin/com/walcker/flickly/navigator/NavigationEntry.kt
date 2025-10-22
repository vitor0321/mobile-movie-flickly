package com.walcker.flickly.navigator

import cafe.adriel.voyager.core.screen.Screen

public interface MoviesEntry {
    fun moviesHome(): Screen
    fun movieDetails(movieId: String): Screen
}

public interface AudioEntry {
    fun audioEntryPoint(): Screen
    fun audioHome(): Screen
}