package com.walcker.flickly.products.movies.features.data.repository

import com.walcker.flickly.products.movies.features.data.mapper.MovieResponseMapper.toDomain
import com.walcker.flickly.products.movies.features.data.network.HttpConfig
import com.walcker.flickly.products.movies.features.domain.api.MovieApi
import com.walcker.flickly.products.movies.features.domain.models.ImageSize
import com.walcker.flickly.products.movies.features.domain.models.Movie
import com.walcker.flickly.products.movies.features.domain.models.MovieSection
import com.walcker.flickly.products.movies.features.domain.models.MoviesPagination
import com.walcker.flickly.products.movies.features.domain.repository.MoviesRepository
import com.walcker.flickly.core.data.handle.withRetry
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.supervisorScope
import kotlinx.coroutines.withContext
import kotlin.text.orEmpty

private const val MAX_HIGHLIGHT_MOVIES = 5

internal class MoviesRepositoryImpl(
    private val movieApi: MovieApi,
    private val dispatcher: CoroutineDispatcher,
) : MoviesRepository {

    override suspend fun getMoviesSections(pagination: MoviesPagination): Result<List<MovieSection>> =
        withContext(dispatcher) {
            runCatching {
                supervisorScope {
                    val movieCategoriesWithPages = MovieSection.SectionType.entries.map { sectionType ->
                        sectionType to pagination.pageFor(sectionType)
                    }
                    val deferredResults = movieCategoriesWithPages.map { (sectionType, page) ->
                        async { runCatching { fetchMoviesByCategory(sectionType, page) } }
                    }
                    val results = deferredResults.awaitAll()

                    val successes = results.filter { it.isSuccess }
                    if (successes.isEmpty()) {
                        val firstFailure = results.firstOrNull { it.isFailure }
                        throw (firstFailure?.exceptionOrNull() ?: RuntimeException("Unknown fetch failure"))
                    }
                    successes.map { it.getOrThrow() }
                }
            }
        }

    override suspend fun getMovieSection(sectionType: MovieSection.SectionType, page: Int): Result<MovieSection> =
        withRetry(dispatcher = dispatcher) {
            runCatching {
                fetchMoviesByCategory(sectionType, page)
            }
        }

    private suspend fun fetchMoviesByCategory(
        sectionType: MovieSection.SectionType,
        page: Int,
    ): MovieSection = withRetry(dispatcher = dispatcher) {
        val movieResponse = movieApi.getMovies(sectionType = sectionType, page = page)
        val imageSize =
            if (sectionType != MovieSection.SectionType.HIGHLIGHT) ImageSize.SMALL
            else ImageSize.MEDIUM

        val moviesList = movieResponse.results.map { it.toDomain(imageSize = imageSize) }

        val processedMovies =
            if (sectionType != MovieSection.SectionType.HIGHLIGHT) moviesList
            else moviesList.shuffled().take(MAX_HIGHLIGHT_MOVIES)

        MovieSection(
            sectionType = sectionType,
            movies = processedMovies.toImmutableList()
        )
    }

    override suspend fun getMovieDetail(movieId: Int): Result<Movie> =
        withRetry(dispatcher = dispatcher) {
            runCatching {
                coroutineScope {
                    val movieDetailDeferred = async { runCatching { movieApi.getMovieDetail(movieId = movieId) } }
                    val creditsDeferred = async { runCatching { movieApi.getCredits(movieId = movieId) } }
                    val videosDeferred = async { runCatching { movieApi.getMovieVideos(movieId = movieId) } }

                    val movieDetailResult = movieDetailDeferred.await()
                    val creditsResult = creditsDeferred.await()
                    val videosResult = videosDeferred.await()

                    if (listOf(movieDetailResult, creditsResult, videosResult).all { it.isFailure }) {
                        throw movieDetailResult.exceptionOrNull()
                            ?: creditsResult.exceptionOrNull()
                            ?: videosResult.exceptionOrNull()
                            ?: RuntimeException("Unknown error")
                    }

                    val movieDetailResponse = movieDetailResult.getOrNull()
                    val creditsResponse = creditsResult.getOrNull()
                    val videosResponse = videosResult.getOrNull()

                    val movieTrailerYoutubeKey = videosResponse?.results
                        ?.firstOrNull()
                        ?.key
                        ?.let { HttpConfig.YOUTUBE_BASE_URL.value + it }

                    movieDetailResponse?.toDomain(
                        castMembersResponse = creditsResponse?.cast.orEmpty(),
                        moviesTrailerYouTubeKey = movieTrailerYoutubeKey,
                        imageSize = ImageSize.X_LARGE,
                    ) ?: throw RuntimeException("Movie detail not available")
                }
            }
        }
}