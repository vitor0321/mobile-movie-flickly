package com.walcker.movies.produto.movies.features.domain.models

internal data class CastMember(
    val id: Int,
    val mainRole: String,
    val name: String,
    val character: String,
    val profileUrl: String?,
)
