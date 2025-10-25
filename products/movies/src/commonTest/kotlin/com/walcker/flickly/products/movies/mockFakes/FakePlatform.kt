package com.walcker.flickly.products.movies.mockFakes

import com.walcker.flickly.core.Platform

internal object FakePlatform {
    fun createMockPlatform(): Platform = object : Platform {
            override val languageSystem: String = "pt-BR"
            override val accessToken: String = "test_token"
    }
}