package com.hamthelegend.enchantmentorder.android.ui.screens.navhost

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.hamthelegend.enchantmentorder.android.ui.screens.NavGraphs
import com.hamthelegend.enchantmentorder.android.ui.screens.defaultTransitions
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterialNavigationApi::class)
@Composable
fun NavHost() {
    val navHostEngine = rememberAnimatedNavHostEngine(
        rootDefaultAnimations = defaultTransitions,
    )

    DestinationsNavHost(
        navGraph = NavGraphs.root,
        engine = navHostEngine,
    )
}