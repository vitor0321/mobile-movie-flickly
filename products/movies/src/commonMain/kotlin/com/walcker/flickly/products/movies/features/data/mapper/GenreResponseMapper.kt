package com.walcker.flickly.products.movies.features.data.mapper

import com.walcker.flickly.products.movies.features.data.models.GenreResponse
import com.walcker.flickly.products.movies.features.domain.models.Genre

internal object GenreResponseMapper {
    fun GenreResponse.toDomain() =
        Genre(
            id = id,
            name = name
        )
}