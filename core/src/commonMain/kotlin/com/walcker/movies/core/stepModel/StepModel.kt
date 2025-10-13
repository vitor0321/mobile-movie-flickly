package com.walcker.movies.core.stepModel

import cafe.adriel.voyager.core.model.StateScreenModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import org.koin.core.logger.Logger

public interface StepContract<S, E> {
    val state: StateFlow<S>
    fun onEvent(event: E)
}

public abstract class StepModel<S, E>(
    initialState: S
) : StateScreenModel<S>(initialState), StepContract<S, E>