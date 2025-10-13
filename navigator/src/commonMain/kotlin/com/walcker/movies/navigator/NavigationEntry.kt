package com.walcker.movies.navigator

import cafe.adriel.voyager.core.screen.Screen

public interface MoviesEntry {
    fun moviesHome(): Screen
    fun movieDetails(movieId: String): Screen
}

public interface BatSignalEntry {
    fun batSignalEntryPoint(): Screen
    fun batSignalHome(): Screen
}