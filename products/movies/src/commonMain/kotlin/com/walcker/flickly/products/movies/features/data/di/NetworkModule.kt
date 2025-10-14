package com.walcker.flickly.products.movies.features.data.di

import com.walcker.flickly.products.movies.features.data.api.MovieApiImpl
import com.walcker.flickly.products.movies.features.data.network.NetworkClientImpl
import com.walcker.flickly.products.movies.features.domain.api.MovieApi
import com.walcker.flickly.products.movies.features.domain.network.NetworkClient
import org.koin.dsl.module

internal val networkModule = module {
    single<NetworkClient> { NetworkClientImpl() }
    single<MovieApi> { MovieApiImpl(networkClient = get(), platform = get()) }
}