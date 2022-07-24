package com.hamthelegend.enchantmentorder.android.ui.screens.addinitialenchantments

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.twotone.Done
import androidx.compose.material.icons.twotone.Forward
import androidx.compose.material.icons.twotone.NextPlan
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hamthelegend.enchantmentorder.android.R
import com.hamthelegend.enchantmentorder.android.ui.common.EnchantmentLevelPicker
import com.hamthelegend.enchantmentorder.android.ui.common.Target
import com.hamthelegend.enchantmentorder.android.ui.screen.Screen
import com.hamthelegend.enchantmentorder.android.ui.theme.EnchantmentOrderTheme
import com.hamthelegend.enchantmentorder.android.ui.theme.ThemeIcons
import com.hamthelegend.enchantmentorder.composables.*
import com.hamthelegend.enchantmentorder.domain.businesslogic.new
import com.hamthelegend.enchantmentorder.domain.models.enchantment.Enchantment
import com.hamthelegend.enchantmentorder.domain.models.enchantment.EnchantmentType
import com.hamthelegend.enchantmentorder.domain.models.item.Item
import com.hamthelegend.enchantmentorder.domain.models.item.ItemType
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
        searchUpdatable = Updatable(viewModel.searchQuery, viewModel::onSearchQueryChange),
        target = viewModel.target,
        enchantmentTypes = viewModel.enchantmentTypes,
        initialEnchantments = viewModel.initialEnchantments,
        addInitialEnchantment = viewModel::addInitialEnchantment,
        removeInitialEnchantment = viewModel::removeInitialEnchantment,
        selectDefaults = viewModel::selectDefaults,
        resetSelection = viewModel::resetSelection,
        navigateToChooseBooksScreen = {},
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AddInitialEnchantments(
    navigateUp: () -> Unit,
    searchUpdatable: Updatable<String>,
    target: ItemType,
    enchantmentTypes: List<EnchantmentType>,
    initialEnchantments: List<Enchantment>,
    addInitialEnchantment: (Enchantment) -> Unit,
    removeInitialEnchantment: (Enchantment) -> Unit,
    selectDefaults: () -> Unit,
    resetSelection: () -> Unit,
    navigateToChooseBooksScreen: () -> Unit,
) {
    val lazyColumnState = rememberLazyListState()
    val scrolled by rememberDerivedStateOf {
        lazyColumnState.firstVisibleItemIndex != 0 ||
                lazyColumnState.firstVisibleItemScrollOffset != 0
    }

    Screen(
        navigateUp = navigateUp,
        title = stringResource(R.string.add_initial_enchantments),
        searchUpdatable = searchUpdatable,
        scrolled = scrolled,
        floatingActionButton = {
            FloatingActionButton(
                onClick = navigateToChooseBooksScreen,
                imageVector = ThemeIcons.Done,
                contentDescription = stringResource(R.string.done),
            )
        }
    ) {
        FullScreenLazyColumn(
            state = lazyColumnState,
            modifier = Modifier.fillMaxSize(),
        ) {
            item {
                Target(
                    target = new(target),
                    hasSelection = initialEnchantments.isNotEmpty(),
                    selectDefaults = selectDefaults,
                    resetSelection = resetSelection,
                    modifier = Modifier.padding(4.dp),
                )
            }
            itemsIndexed(
                items = enchantmentTypes,
                key = { _, enchantmentType ->
                    enchantmentType.friendlyName
                },
            ) { index, enchantmentType ->
                val initialEnchantment =
                    initialEnchantments.firstOrNull { it.type == enchantmentType }
                val topActive = initialEnchantments.any { enchantment ->
                    enchantment.type == enchantmentTypes.getOrNull(index - 1)
                }
                val bottomActive = initialEnchantments.any { enchantment ->
                    enchantment.type == enchantmentTypes.getOrNull(index + 1)
                }

                EnchantmentLevelPicker(
                    enchantmentType = enchantmentType,
                    level = initialEnchantment?.level,
                    select = { enchantment -> addInitialEnchantment(enchantment) },
                    deselect = { enchantment -> removeInitialEnchantment(enchantment) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .animateItemPlacement(),
                    topActive = topActive,
                    bottomActive = bottomActive,
                )
            }
        }
    }
}

@Preview
@Composable
fun AddInitialEnchantmentsPreview() {
    EnchantmentOrderTheme {
        var search by rememberMutableStateOf(value = "")
        val target = ItemType.Pickaxe
        var enchantmentTypes by rememberMutableStateOf(value = target.compatibleEnchantmentTypes.toList())
        var initialEnchantments by rememberMutableStateOf(value = emptyList<Enchantment>())

        AddInitialEnchantments(navigateUp = {},
            searchUpdatable = Updatable(search) { query ->
                search = query
                enchantmentTypes =
                    target.compatibleEnchantmentTypes.filter { query in it.friendlyName }
            },
            target = ItemType.Pickaxe,
            enchantmentTypes = enchantmentTypes,
            initialEnchantments = initialEnchantments,
            addInitialEnchantment = {
                initialEnchantments += it
            },
            removeInitialEnchantment = {
                initialEnchantments -= it
            },
            selectDefaults = {
                initialEnchantments = enchantmentTypes.map { Enchantment(it, it.maxLevel) }
            },
            resetSelection = {
                initialEnchantments = emptyList()
            },
            navigateToChooseBooksScreen = {},
        )
    }
}