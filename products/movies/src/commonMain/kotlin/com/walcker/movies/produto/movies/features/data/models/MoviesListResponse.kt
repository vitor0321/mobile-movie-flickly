package com.walcker.movies.produto.movies.features.data.models

import kotlinx.datetime.LocalDate
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class MovieListResponse(
    val results: List<MovieResponse>
)

@Serializable
internal data class MovieResponse(
    val id: Int,
    val title: String,
    val overview: String,
    @SerialName("poster_path")
    val posterPath: String? = null,
    val genres: List<GenreResponse>? = null,
    @SerialName("release_date")
    val releaseDate: LocalDate = LocalDate(2023, 1, 1),
    val runtime: Int? = null,
    @SerialName("vote_average")
    val voteAverage: Double = 0.0,
)

@Serializable
internal data class GenreResponse(
    val id: Int,
    val name: String,
)