package com.hamthelegend.enchantmentorder.android.ui.screens.choosetarget

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.hamthelegend.enchantmentorder.android.R
import com.hamthelegend.enchantmentorder.android.ui.screen.Screen
import com.hamthelegend.enchantmentorder.android.ui.screens.chooseedition.ChooseEdition
import com.hamthelegend.enchantmentorder.composables.Updatable
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(navArgsDelegate = ChooseTargetNavArgs::class)
@Composable
fun ChooseTargetScreen(
    navigator: DestinationsNavigator,
    viewModel: ChooseTargetViewModel = hiltViewModel(),
) {

    ChooseTarget(
        navigateUp = navigator::navigateUp,
        searchUpdatable = Updatable(viewModel.searchQuery, viewModel::onSearchQueryChange)
    )

}

@Composable
fun ChooseTarget(
    navigateUp: () -> Unit,
    searchUpdatable: Updatable<String>,
) {
    Screen(
        title = stringResource(R.string.choose_target),
        navigateUp = navigateUp,
        searchUpdatable = searchUpdatable,
    ) {

    }
}