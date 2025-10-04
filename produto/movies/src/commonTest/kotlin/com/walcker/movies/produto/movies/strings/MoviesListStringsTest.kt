package com.walcker.movies.produto.movies.strings

import com.walcker.movies.produto.movies.strings.features.moviesListStringsEn
import com.walcker.movies.produto.movies.strings.features.moviesListStringsPt
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

internal class MoviesListStringsTest {

    @Test
    fun `given moviesListStringsPt when accessing properties then should return Portuguese strings`() {
        // Given & When
        val ptStrings = moviesListStringsPt

        // Then
        assertNotNull(ptStrings)
        assertEquals("Flickly", ptStrings.appName)
        assertEquals("Populares", ptStrings.popularMovies)
        assertEquals("Mais Bem Avaliados", ptStrings.topRatedMovies)
        assertEquals("Em Breve", ptStrings.upcomingMovies)
    }

    @Test
    fun `given moviesListStringsEn when accessing properties then should return English strings`() {
        // Given & When
        val enStrings = moviesListStringsEn

        // Then
        assertNotNull(enStrings)
        assertEquals("Flickly", enStrings.appName)
        assertEquals("Popular", enStrings.popularMovies)
        assertEquals("Top Rated", enStrings.topRatedMovies)
        assertEquals("Upcoming", enStrings.upcomingMovies)
    }
}