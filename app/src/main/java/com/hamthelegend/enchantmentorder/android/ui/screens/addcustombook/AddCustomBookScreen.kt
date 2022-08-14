package com.hamthelegend.enchantmentorder.android.ui.screens.addcustombook

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.twotone.Add
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hamthelegend.enchantmentorder.android.R
import com.hamthelegend.enchantmentorder.android.ui.common.InfoCard
import com.hamthelegend.enchantmentorder.android.ui.common.Target
import com.hamthelegend.enchantmentorder.android.ui.common.itemsForEnchantmentPicker
import com.hamthelegend.enchantmentorder.android.ui.screen.LazyColumnScreen
import com.hamthelegend.enchantmentorder.android.ui.common.RenamingCostDialog
import com.hamthelegend.enchantmentorder.android.ui.screens.SubscriptionViewModel
import com.hamthelegend.enchantmentorder.android.ui.screens.choosebooks.ChooseBooksNavGraph
import com.hamthelegend.enchantmentorder.android.ui.screens.choosebooks.ChooseBooksViewModel
import com.hamthelegend.enchantmentorder.android.ui.theme.EnchantmentOrderTheme
import com.hamthelegend.enchantmentorder.android.ui.theme.ThemeIcons
import com.hamthelegend.enchantmentorder.composables.FloatingActionButton
import com.hamthelegend.enchantmentorder.composables.VerticalSpacer
import com.hamthelegend.enchantmentorder.composables.rememberMutableStateOf
import com.hamthelegend.enchantmentorder.domain.extensions.new
import com.hamthelegend.enchantmentorder.domain.models.enchantment.Enchantment
import com.hamthelegend.enchantmentorder.domain.models.enchantment.EnchantmentType
import com.hamthelegend.enchantmentorder.domain.models.item.Item
import com.hamthelegend.enchantmentorder.domain.models.item.ItemType
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch

@ChooseBooksNavGraph
@Destination(navArgsDelegate = AddCustomBookNavArgs::class)
@Composable
fun AddCustomBookScreen(
    navigator: DestinationsNavigator,
    chooseBooksViewModel: ChooseBooksViewModel,
    subscriptionViewModel: SubscriptionViewModel,
    viewModel: AddCustomBookViewModel = hiltViewModel(),
) {
    AddCustomBook(
        navigateUp = navigator::navigateUp,
        searchQuery = viewModel.searchQuery,
        onSearchQueryChange = viewModel::onSearchQueryChange,
        premium = subscriptionViewModel.premium ?: false,
        target = viewModel.target,
        enchantmentTypes = viewModel.enchantmentTypes,
        enchantmentTypeOnFocus = viewModel.enchantmentTypeOnFocus,
        onEnchantmentTypeOnFocusChanged = viewModel::onEnchantmentTypeOnFocusChanged,
        bookEnchantments = viewModel.bookEnchantments,
        addBookEnchantment = viewModel::addBookEnchantment,
        removeBookEnchantment = viewModel::removeBookEnchantment,
        selectDefaults = viewModel::selectDefaults,
        resetSelection = viewModel::resetSelection,
        renamingCostDialogVisible = viewModel.renamingCostDialogVisible,
        showRenamingCostDialog = viewModel::showRenamingCostDialog,
        hideRenamingCostDialog = viewModel::hideRenamingCostDialog,
        isBookCompatible = {
            chooseBooksViewModel.isBookCompatible(viewModel.compileCustomBook())
        },
        addCustomBook = { renamingCost ->
            chooseBooksViewModel.addCustomBook(viewModel.compileCustomBook(renamingCost))
        },
    )
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AddCustomBook(
    navigateUp: () -> Unit,
    searchQuery: String,
    onSearchQueryChange: (newQuery: String) -> Unit,
    premium: Boolean,
    target: Item,
    enchantmentTypes: List<EnchantmentType>,
    enchantmentTypeOnFocus: EnchantmentType?,
    onEnchantmentTypeOnFocusChanged: (newEnchantmentTypeOnFocus: EnchantmentType?) -> Unit,
    bookEnchantments: List<Enchantment>,
    addBookEnchantment: (Enchantment) -> Unit,
    removeBookEnchantment: (Enchantment) -> Unit,
    selectDefaults: () -> Unit,
    resetSelection: () -> Unit,
    renamingCostDialogVisible: Boolean,
    showRenamingCostDialog: () -> Unit,
    hideRenamingCostDialog: () -> Unit,
    isBookCompatible: () -> Boolean,
    addCustomBook: (renamingCost: Int) -> Unit,
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val snackbarHostStateScope = rememberCoroutineScope()
    val noCompatibleEnchantmentsString = stringResource(R.string.no_compatible_enchantments)

    if (renamingCostDialogVisible) {
        RenamingCostDialog(
            dismiss = hideRenamingCostDialog,
            confirm = { renamingCost ->
                addCustomBook(renamingCost)
                navigateUp()
            },
            itemType = ItemType.EnchantedBook,
        )
    }

    LazyColumnScreen(
        title = stringResource(id = R.string.add_custom_book),
        navigateUp = navigateUp,
        searchQuery = searchQuery,
        onSearchQueryChange = onSearchQueryChange,
        showAd = !premium,
        snackbarHostState = snackbarHostState,
        floatingActionButton = { modifier ->
            val fabVisible = bookEnchantments.isNotEmpty()

            AnimatedVisibility(
                visible = fabVisible,
                enter = scaleIn(),
                exit = scaleOut(),
            ) {
                FloatingActionButton(
                    onClick = {
                        if (isBookCompatible()) {
                            showRenamingCostDialog()
                        } else {
                            snackbarHostStateScope.launch {
                                snackbarHostState.showSnackbar(noCompatibleEnchantmentsString)
                            }
                        }
                    },
                    imageVector = ThemeIcons.Add,
                    contentDescription = stringResource(R.string.add),
                    modifier = modifier,
                )
            }
        }
    ) {
        item {
            InfoCard(
                text = "Add a custom enchanted book with one or more enchantments of varying levels.",
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
                target = target,
                hasSelection = bookEnchantments.isNotEmpty(),
                selectDefaults = selectDefaults,
                resetSelection = resetSelection,
                modifier = Modifier.padding(4.dp)
            )
        }
        itemsForEnchantmentPicker(
            enchantmentTypes = enchantmentTypes,
            selectedEnchantments = bookEnchantments,
            enchantmentTypeOnFocus = enchantmentTypeOnFocus,
            onEnchantmentTypeOnFocusChanged = onEnchantmentTypeOnFocusChanged,
            selectEnchantment = addBookEnchantment,
            deselectEnchantment = removeBookEnchantment,
        )
    }
}

@Preview
@Composable
fun AddCustomBookScreenPreview() {
    EnchantmentOrderTheme {
        var enchantmentTypeOnFocus: EnchantmentType? by rememberMutableStateOf(value = null)

        AddCustomBook(
            navigateUp = {},
            searchQuery = "",
            onSearchQueryChange = {},
            premium = false,
            target = new(ItemType.Pickaxe),
            enchantmentTypes = ItemType.Pickaxe.compatibleEnchantmentTypes.toList(),
            enchantmentTypeOnFocus = enchantmentTypeOnFocus,
            onEnchantmentTypeOnFocusChanged = { enchantmentTypeOnFocus = it },
            bookEnchantments = emptyList(),
            addBookEnchantment = {},
            removeBookEnchantment = {},
            selectDefaults = {},
            resetSelection = {},
            renamingCostDialogVisible = false,
            showRenamingCostDialog = {},
            hideRenamingCostDialog = {},
            isBookCompatible = { true },
            addCustomBook = {},
        )
    }
}