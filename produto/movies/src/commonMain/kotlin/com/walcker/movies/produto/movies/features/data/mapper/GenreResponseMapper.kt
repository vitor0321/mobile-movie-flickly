package com.walcker.movies.produto.movies.features.data.mapper

import com.walcker.movies.produto.movies.features.data.models.GenreResponse
import com.walcker.movies.produto.movies.features.domain.models.Genre

internal object GenreResponseMapper {
    fun GenreResponse.toDomain() =
        Genre(
            id = id,
            name = name
        )
}