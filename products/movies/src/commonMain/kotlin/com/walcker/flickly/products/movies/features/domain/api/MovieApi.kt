package com.walcker.flickly.products.movies.features.domain.api

import com.walcker.flickly.products.movies.features.data.models.CreditsResponse
import com.walcker.flickly.products.movies.features.data.models.MovieListResponse
import com.walcker.flickly.products.movies.features.data.models.MovieResponse
import com.walcker.flickly.products.movies.features.data.models.VideoResponse
import com.walcker.flickly.products.movies.features.domain.models.MovieSection

internal interface MovieApi {
    suspend fun getMovies(
        sectionType: MovieSection.SectionType,
        page: Int,
    ): MovieListResponse
    suspend fun getMovieDetail(movieId: Int): MovieResponse
    suspend fun getCredits(movieId: Int): CreditsResponse
    suspend fun getMovieVideos(movieId: Int): VideoResponse
}