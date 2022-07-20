package com.hamthelegend.enchantmentorder.android.ui.screens

import androidx.compose.animation.ExperimentalAnimationApi
import com.hamthelegend.enchantmentorder.composables.sharedZAxisEnter
import com.hamthelegend.enchantmentorder.composables.sharedZAxisExit
import com.ramcosta.composedestinations.animations.defaults.DestinationEnterTransition
import com.ramcosta.composedestinations.animations.defaults.RootNavGraphDefaultAnimations

@OptIn(ExperimentalAnimationApi::class)
val defaultTransitions = RootNavGraphDefaultAnimations(
    enterTransition = { sharedZAxisEnter() },
    exitTransition = { sharedZAxisExit() },
    popEnterTransition = { sharedZAxisEnter(fromFront = true) },
    popExitTransition = { sharedZAxisExit(fromFront = true) },
)