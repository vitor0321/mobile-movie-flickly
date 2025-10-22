package com.walcker.flickly.core.data.handle

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.retryWhen
import kotlin.math.pow

/**
 * Retry helper for suspend functions.
 */
public sealed class RetryStrategy(val getDelay: (attempt: Int) -> Long) {
    public data class Exponential(val ratio: Double = 2.0) : RetryStrategy(
        getDelay = { attempt ->
            if (attempt == 0) {
                0
            } else {
                ratio.pow(attempt).toLong() * 1000
            }
        },
    )
}

public suspend fun <T> withRetry(
    dispatcher: CoroutineDispatcher = Dispatchers.IO,
    maxAttempts: Int = 4,
    strategy: RetryStrategy = RetryStrategy.Exponential(),
    shouldRetry: suspend (Throwable) -> Boolean = { it is RuntimeException },
    action: suspend () -> T,
): T = flow { emit(action()) }
    .retryWhen { cause, attempt ->
        if (shouldRetry(cause) && attempt < maxAttempts) {
            delay(strategy.getDelay(attempt.toInt()))
            true
        } else {
            false
        }
    }
    .flowOn(dispatcher)
    .first()