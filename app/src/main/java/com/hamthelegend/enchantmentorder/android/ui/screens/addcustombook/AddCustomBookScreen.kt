package com.hamthelegend.enchantmentorder.android.ui.screens.addcustombook

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.twotone.Add
import androidx.compose.material.icons.twotone.Done
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.hamthelegend.enchantmentorder.android.R
import com.hamthelegend.enchantmentorder.android.ui.common.Target
import com.hamthelegend.enchantmentorder.android.ui.common.itemsForEnchantmentPicker
import com.hamthelegend.enchantmentorder.android.ui.screen.ScreenWithLazyColumn
import com.hamthelegend.enchantmentorder.android.ui.screens.addinitialenchantments.RenamingCostDialog
import com.hamthelegend.enchantmentorder.android.ui.theme.ThemeIcons
import com.hamthelegend.enchantmentorder.composables.FloatingActionButton
import com.hamthelegend.enchantmentorder.domain.models.enchantment.Enchantment
import com.hamthelegend.enchantmentorder.domain.models.enchantment.EnchantmentType
import com.hamthelegend.enchantmentorder.domain.models.item.Item
import com.ramcosta.composedestinations.annotation.Destination

@Destination(navArgsDelegate = AddCustomBookNavArgs::class)
@Composable
fun AddCustomBookScreen() {

}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AddCustomBook(
    navigateUp: () -> Unit,
    searchQuery: String,
    onSearchQueryChange: (newQuery: String) -> Unit,
    target: Item,
    enchantmentTypes: List<EnchantmentType>,
    bookEnchantments: List<Enchantment>,
    addBookEnchantment: (Enchantment) -> Unit,
    removeBookEnchantment: (Enchantment) -> Unit,
    selectDefaults: () -> Unit,
    resetSelection: () -> Unit,
    renamingCostDialogVisible: Boolean,
    showRenamingCostDialog: () -> Unit,
    hideRenamingCostDialog:() -> Unit,
    addCustomBook: (renamingCost: Int) -> Unit,
) {
    if (renamingCostDialogVisible) {
        RenamingCostDialog(
            dismiss = hideRenamingCostDialog,
            confirm = { renamingCost ->
//                val addSuccessful = addCustomBook(renamingCost)
            },
        )
    }

    ScreenWithLazyColumn(
        title = stringResource(id = R.string.add_custom_book),
        navigateUp = navigateUp,
        searchQuery = searchQuery,
        onSearchQueryChange = onSearchQueryChange,
        floatingActionButton = {
            val fabVisible = bookEnchantments.isNotEmpty()

            AnimatedVisibility(
                visible = fabVisible,
                enter = scaleIn(),
                exit = scaleOut(),
            ) {
                FloatingActionButton(
                    onClick = showRenamingCostDialog,
                    imageVector = ThemeIcons.Add,
                    contentDescription = stringResource(R.string.add),
                    modifier = Modifier.navigationBarsPadding(),
                )
            }
        }
    ) {
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
            selectEnchantment = addBookEnchantment,
            deselectEnchantment = removeBookEnchantment,
        )
    }
}