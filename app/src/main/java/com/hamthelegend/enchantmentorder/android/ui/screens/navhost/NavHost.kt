package com.hamthelegend.enchantmentorder.android.ui.screens.navhost

import androidx.compose.runtime.Composable
import com.hamthelegend.enchantmentorder.android.ui.screens.home.NavGraphs
import com.ramcosta.composedestinations.DestinationsNavHost

@Composable
fun NavHost() {
    DestinationsNavHost(navGraph = NavGraphs.root)
}