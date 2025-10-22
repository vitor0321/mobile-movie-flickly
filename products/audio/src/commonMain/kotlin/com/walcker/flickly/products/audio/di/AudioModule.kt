package com.walcker.flickly.products.audio.di

import com.walcker.flickly.products.audio.features.data.di.managerModule
import com.walcker.flickly.products.audio.features.ui.di.stepModelModule
import com.walcker.flickly.products.audio.navigation.navigationModule
import org.koin.core.module.Module

public val audioModule = listOf<Module>(
    //data
    managerModule,

    // Features
    stepModelModule,

    // Navigator
    navigationModule,
)