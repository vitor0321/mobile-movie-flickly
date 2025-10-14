package com.walcker.flickly.products.movies.features.domain.repository

import com.walcker.flickly.products.movies.features.domain.models.Movie
import com.walcker.flickly.products.movies.features.domain.models.MovieSection
import com.walcker.flickly.products.movies.features.domain.models.MoviesPagination

internal interface MoviesRepository {

    suspend fun getMoviesSections(pagination: MoviesPagination): Result<List<MovieSection>>
    suspend fun getMovieSection(sectionType: MovieSection.SectionType, page: Int): Result<MovieSection>
    suspend fun getMovieDetail(movieId: Int): Result<Movie>
}