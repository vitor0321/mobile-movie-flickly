package com.walcker.movies.produto.movies.mockData.domain

import com.walcker.movies.produto.movies.features.domain.models.Movie
import kotlinx.collections.immutable.persistentListOf

internal val movieTestData = Movie(
    id = 1,
    title = "Test Movie",
    overview = "Test Overview",
    posterUrl = "https://image.tmdb.org/t/p/w185//test.jpg",
    genres = persistentListOf(genre1TestData),
    year = "2024",
    duration = "2h ",
    rating = "8.5",
    castMembers = null,
    moviesTrailerYouTubeKey = "vasdfOUBFKkaasdf"
)

internal val movieTestData1 = Movie(
    id = 1,
    title = "Test Movie",
    overview = "Test Overview",
    posterUrl = "https://image.tmdb.org/t/p/w185//test.jpg",
    genres = persistentListOf(genre1TestData),
    year = "2024",
    duration = "2h ",
    rating = "8.5",
    castMembers = castMemberListTestData,
    moviesTrailerYouTubeKey = null
)