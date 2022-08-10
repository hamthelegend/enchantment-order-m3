package com.hamthelegend.enchantmentorder.android.ui.screens.choosetarget

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.twotone.Info
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hamthelegend.enchantmentorder.android.R
import com.hamthelegend.enchantmentorder.android.ui.common.InfoCard
import com.hamthelegend.enchantmentorder.android.ui.res.imageResId
import com.hamthelegend.enchantmentorder.android.ui.screen.LazyColumnScreen
import com.hamthelegend.enchantmentorder.android.ui.screens.destinations.AddInitialEnchantmentsScreenDestination
import com.hamthelegend.enchantmentorder.android.ui.theme.EnchantmentOrderTheme
import com.hamthelegend.enchantmentorder.android.ui.theme.ThemeIcons
import com.hamthelegend.enchantmentorder.composables.*
import com.hamthelegend.enchantmentorder.domain.extensions.targetableItemTypes
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
        searchQuery = viewModel.searchQuery,
        onSearchQueryChange = viewModel::onSearchQueryChange,
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

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ChooseTarget(
    navigateUp: () -> Unit,
    searchQuery: String,
    onSearchQueryChange: (newQuery: String) -> Unit,
    targets: List<ItemType>,
    navigateToAddInitialEnchantmentsScreen: (target: ItemType) -> Unit,
) {
    LazyColumnScreen(
        title = stringResource(R.string.choose_target),
        navigateUp = navigateUp,
        searchQuery = searchQuery,
        onSearchQueryChange = onSearchQueryChange,
    ) {
        item {
            InfoCard(
                text = stringResource(R.string.choose_target_info),
                modifier = Modifier.fillMaxWidth().padding(4.dp),
            )
        }
        items(
            items = targets,
            key = { itemType -> itemType.friendlyName },
        ) { itemType ->
            IconTextCard(
                painterResourceId = itemType.imageResId,
                text = itemType.friendlyName,
                onClick = { navigateToAddInitialEnchantmentsScreen(itemType) },
                modifier = Modifier
                    .fillMaxWidth()
                    .animateItemPlacement(),
            )
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
            searchQuery = searchQuery,
            onSearchQueryChange = { searchQuery = it },
            targets = targets,
            navigateToAddInitialEnchantmentsScreen = {},
        )

    }
}