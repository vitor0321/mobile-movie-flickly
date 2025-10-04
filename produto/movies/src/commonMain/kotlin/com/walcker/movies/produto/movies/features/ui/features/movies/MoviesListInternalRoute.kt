package com.walcker.movies.produto.movies.features.ui.features.movies

import com.walcker.movies.produto.movies.features.domain.models.MovieSection

internal interface MoviesListInternalRoute {
    data class OnLoadNextPage(val sectionType: MovieSection.SectionType) : MoviesListInternalRoute
}