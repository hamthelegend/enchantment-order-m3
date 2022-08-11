package com.hamthelegend.enchantmentorder.android.ui.screens.addinitialenchantments

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.twotone.Done
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
import com.hamthelegend.enchantmentorder.android.ui.common.RenamingCostDialog
import com.hamthelegend.enchantmentorder.android.ui.common.Target
import com.hamthelegend.enchantmentorder.android.ui.common.itemsForEnchantmentPicker
import com.hamthelegend.enchantmentorder.android.ui.screen.LazyColumnScreen
import com.hamthelegend.enchantmentorder.android.ui.screens.destinations.ChooseBooksScreenDestination
import com.hamthelegend.enchantmentorder.android.ui.theme.EnchantmentOrderTheme
import com.hamthelegend.enchantmentorder.android.ui.theme.ThemeIcons
import com.hamthelegend.enchantmentorder.composables.*
import com.hamthelegend.enchantmentorder.domain.extensions.forEdition
import com.hamthelegend.enchantmentorder.domain.extensions.new
import com.hamthelegend.enchantmentorder.domain.extensions.removeIncompatibleWith
import com.hamthelegend.enchantmentorder.domain.extensions.renamingCostToAnvilUseCount
import com.hamthelegend.enchantmentorder.domain.models.edition.Edition
import com.hamthelegend.enchantmentorder.domain.models.enchantment.Enchantment
import com.hamthelegend.enchantmentorder.domain.models.enchantment.EnchantmentType
import com.hamthelegend.enchantmentorder.domain.models.item.Item
import com.hamthelegend.enchantmentorder.domain.models.item.ItemType
import com.hamthelegend.enchantmentorder.extensions.search
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(navArgsDelegate = AddInitialEnchantmentsNavArgs::class)
@Composable
fun AddInitialEnchantmentsScreen(
    navigator: DestinationsNavigator,
    viewModel: AddInitialEnchantmentsViewModel = hiltViewModel(),
) {
    AddInitialEnchantments(
        navigateUp = navigator::navigateUp,
        searchQuery = viewModel.searchQuery,
        onSearchQueryChange = viewModel::onSearchQueryChange,
        target = viewModel.target,
        enchantmentTypes = viewModel.enchantmentTypes,
        initialEnchantments = viewModel.initialEnchantments,
        addInitialEnchantment = viewModel::addInitialEnchantment,
        removeInitialEnchantment = viewModel::removeInitialEnchantment,
        selectDefaults = viewModel::selectDefaults,
        resetSelection = viewModel::resetSelection,
        renamingCostDialogVisible = viewModel.renamingCostDialogVisible,
        showRenamingCostDialog = viewModel::showRenamingCostDialog,
        hideRenamingCostDialog = viewModel::hideRenamingCostDialog,
        navigateToChooseBooksScreen = { renamingCost ->
            navigator.navigate(
                ChooseBooksScreenDestination(
                    edition = viewModel.edition,
                    target = Item(
                        type = viewModel.target,
                        enchantments = viewModel.initialEnchantments,
                        anvilUseCount = renamingCost.renamingCostToAnvilUseCount(),
                    )
                )
            )
        },
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AddInitialEnchantments(
    navigateUp: () -> Unit,
    searchQuery: String,
    onSearchQueryChange: (newQuery: String) -> Unit,
    target: ItemType,
    enchantmentTypes: List<EnchantmentType>,
    initialEnchantments: List<Enchantment>,
    addInitialEnchantment: (Enchantment) -> Unit,
    removeInitialEnchantment: (Enchantment) -> Unit,
    selectDefaults: () -> Unit,
    resetSelection: () -> Unit,
    renamingCostDialogVisible: Boolean,
    showRenamingCostDialog: () -> Unit,
    hideRenamingCostDialog: () -> Unit,
    navigateToChooseBooksScreen: (renamingCost: Int) -> Unit,
) {
    if (renamingCostDialogVisible) {
        RenamingCostDialog(
            dismiss = hideRenamingCostDialog,
            confirm = { renamingCost ->
                navigateToChooseBooksScreen(renamingCost)
            },
            itemType = target,
        )
    }

    LazyColumnScreen(
        navigateUp = navigateUp,
        title = stringResource(R.string.add_initial_enchantments),
        searchQuery = searchQuery,
        onSearchQueryChange = onSearchQueryChange,
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    if (initialEnchantments.isNotEmpty()) {
                        showRenamingCostDialog()
                    } else {
                        navigateToChooseBooksScreen(1)
                    }
                },
                imageVector = ThemeIcons.Done,
                contentDescription = stringResource(R.string.done),
                modifier = Modifier.navigationBarsPadding(),
            )
        }
    ) {
        item {
            InfoCard(
                text = stringResource(
                    R.string.add_initial_enchantments_info,
                    target.friendlyName.lowercase(),
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        start = 4.dp,
                        top = 4.dp,
                        end = 4.dp,
                        bottom = 0.dp
                    ),
            )
        }
        item {
            Target(
                target = new(target),
                hasSelection = initialEnchantments.isNotEmpty(),
                selectDefaults = selectDefaults,
                resetSelection = resetSelection,
                modifier = Modifier.padding(4.dp),
            )
        }
        itemsForEnchantmentPicker(
            enchantmentTypes,
            initialEnchantments,
            addInitialEnchantment,
            removeInitialEnchantment,
        )
    }
}

@Preview
@Composable
fun AddInitialEnchantmentsPreview() {

    EnchantmentOrderTheme {
        val edition = Edition.Java
        var searchQuery by rememberMutableStateOf(value = "")
        val target = ItemType.Pickaxe
        var enchantmentTypes by rememberMutableStateOf(value = target.compatibleEnchantmentTypes.toList())
        var initialEnchantments by rememberMutableStateOf(value = emptyList<Enchantment>())
        var renamingCostDialogVisible by rememberMutableStateOf(value = false)

        fun refreshEnchantmentTypes() {
            enchantmentTypes = target.compatibleEnchantmentTypes
                .forEdition(edition)
                .search(searchQuery) { it.friendlyName }
                .removeIncompatibleWith(initialEnchantments.map { it.type })
        }

        AddInitialEnchantments(
            navigateUp = {},
            searchQuery = searchQuery,
            onSearchQueryChange = {
                searchQuery = it
                refreshEnchantmentTypes()
            },
            target = ItemType.Pickaxe,
            enchantmentTypes = enchantmentTypes,
            initialEnchantments = initialEnchantments,
            addInitialEnchantment = {
                initialEnchantments += it
                refreshEnchantmentTypes()
            },
            removeInitialEnchantment = {
                initialEnchantments -= it
                refreshEnchantmentTypes()
            },
            selectDefaults = {
                initialEnchantments = enchantmentTypes.map { Enchantment(it, it.maxLevel) }
                refreshEnchantmentTypes()
            },
            resetSelection = {
                initialEnchantments = emptyList()
                refreshEnchantmentTypes()
            },
            renamingCostDialogVisible = renamingCostDialogVisible,
            showRenamingCostDialog = { renamingCostDialogVisible = true },
            hideRenamingCostDialog = { renamingCostDialogVisible = false },
            navigateToChooseBooksScreen = {},
        )
    }
}