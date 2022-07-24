package com.hamthelegend.enchantmentorder.android.ui.screens.choosetarget

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.hamthelegend.enchantmentorder.android.R
import com.hamthelegend.enchantmentorder.android.ui.res.imageResId
import com.hamthelegend.enchantmentorder.android.ui.screen.Screen
import com.hamthelegend.enchantmentorder.android.ui.screens.destinations.AddInitialEnchantmentsScreenDestination
import com.hamthelegend.enchantmentorder.android.ui.theme.EnchantmentOrderTheme
import com.hamthelegend.enchantmentorder.composables.*
import com.hamthelegend.enchantmentorder.domain.businesslogic.targetableItemTypes
import com.hamthelegend.enchantmentorder.domain.models.item.ItemType
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
        searchUpdatable = Updatable(viewModel.searchQuery, viewModel::onSearchQueryChange),
        targets = viewModel.targets,
        navigateToAddInitialEnchantmentsScreen = { target ->
            navigator.navigate(
                AddInitialEnchantmentsScreenDestination(
                    edition = viewModel.edition,
                    target = target,
                )
            )
        }
    )

}

@Composable
fun ChooseTarget(
    navigateUp: () -> Unit,
    searchUpdatable: Updatable<String>,
    targets: List<ItemType>,
    navigateToAddInitialEnchantmentsScreen: (target: ItemType) -> Unit,
) {
    val lazyColumnState = rememberLazyListState()
    val scrolled by rememberDerivedStateOf {
        lazyColumnState.firstVisibleItemIndex != 0 ||
                lazyColumnState.firstVisibleItemScrollOffset != 0
    }

    Screen(
        title = stringResource(R.string.choose_target),
        navigateUp = navigateUp,
        searchUpdatable = searchUpdatable,
        scrolled = scrolled,
    ) {
        FullScreenLazyColumn(
            state = lazyColumnState,
            modifier = Modifier.fillMaxSize(),
        ) {
            items(
                items = targets,
                key = { itemType -> itemType.friendlyName },
            ) { itemType ->
                ImageTextCard(
                    painterResourceId = itemType.imageResId,
                    text = itemType.friendlyName,
                    onClick = { navigateToAddInitialEnchantmentsScreen(itemType) },
                    modifier = Modifier.fillMaxWidth(),
                )
            }
        }
    }
}

@Preview
@Composable
fun ChooseTargetPreview() {
    EnchantmentOrderTheme {

        var searchQuery by rememberMutableStateOf(value = "")
        val targets = targetableItemTypes.filter { searchQuery in it.friendlyName }

        ChooseTarget(
            navigateUp = {},
            searchUpdatable = Updatable(searchQuery) { searchQuery = it },
            targets = targets,
            navigateToAddInitialEnchantmentsScreen = {},
        )

    }
}