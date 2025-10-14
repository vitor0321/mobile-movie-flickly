package com.walcker.flickly.core

interface Platform {
    val languageSystem: String
    val accessToken: String
}

expect fun platformImpl(): Platform