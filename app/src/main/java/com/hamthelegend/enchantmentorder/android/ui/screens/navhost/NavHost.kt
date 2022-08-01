package com.hamthelegend.enchantmentorder.android.ui.screens.navhost

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.hamthelegend.enchantmentorder.android.ui.screens.NavGraphs
import com.hamthelegend.enchantmentorder.android.ui.screens.choosebooks.ChooseBooksViewModel
import com.hamthelegend.enchantmentorder.android.ui.screens.defaultTransitions
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine
import com.ramcosta.composedestinations.navigation.dependency

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterialNavigationApi::class)
@Composable
fun NavHost() {
    val navHostEngine = rememberAnimatedNavHostEngine(
        rootDefaultAnimations = defaultTransitions,
    )

    DestinationsNavHost(
        navGraph = NavGraphs.root,
        engine = navHostEngine,
        dependenciesContainerBuilder = {
            dependency(NavGraphs.chooseBooks) {
                val parentEntry = remember(navBackStackEntry) {
                    navController.getBackStackEntry(NavGraphs.chooseBooks.route)
                }
                hiltViewModel<ChooseBooksViewModel>(parentEntry)
            }
        }
    )
}