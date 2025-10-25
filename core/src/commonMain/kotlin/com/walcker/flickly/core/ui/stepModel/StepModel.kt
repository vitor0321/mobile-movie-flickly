package com.walcker.flickly.core.ui.stepModel

import cafe.adriel.voyager.core.model.StateScreenModel
import kotlinx.coroutines.flow.StateFlow

public interface StepContract<S, E> {
    val state: StateFlow<S>
    fun onEvent(event: E)
}

public abstract class StepModel<S, E>(
    initialState: S
) : StateScreenModel<S>(initialState), StepContract<S, E>