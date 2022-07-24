package com.hamthelegend.enchantmentorder.android.ui.screens.choosebooks

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.twotone.Add
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hamthelegend.enchantmentorder.android.R
import com.hamthelegend.enchantmentorder.android.ui.common.Target
import com.hamthelegend.enchantmentorder.android.ui.screen.Screen
import com.hamthelegend.enchantmentorder.android.ui.screen.ScreenWithLazyColumn
import com.hamthelegend.enchantmentorder.android.ui.theme.EnchantmentOrderTheme
import com.hamthelegend.enchantmentorder.android.ui.theme.ThemeIcons
import com.hamthelegend.enchantmentorder.composables.ImageTextCard
import com.hamthelegend.enchantmentorder.composables.rememberMutableStateOf
import com.hamthelegend.enchantmentorder.domain.businesslogic.*
import com.hamthelegend.enchantmentorder.domain.models.edition.Edition
import com.hamthelegend.enchantmentorder.domain.models.enchantment.Enchantment
import com.hamthelegend.enchantmentorder.domain.models.enchantment.EnchantmentType
import com.hamthelegend.enchantmentorder.domain.models.item.Item
import com.hamthelegend.enchantmentorder.domain.models.item.ItemType
import com.hamthelegend.enchantmentorder.extensions.search
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(navArgsDelegate = ChooseBooksNavArgs::class)
@Composable
fun ChooseBooksScreen(
    navigator: DestinationsNavigator,
    viewModel: ChooseBooksViewModel = hiltViewModel(),
) {
    ChooseBooks(
        navigateUp = navigator::navigateUp,
        searchQuery = viewModel.searchQuery,
        onSearchQueryChange = viewModel::onSearchQueryChange,
        target = viewModel.target,
        customBooks = viewModel.customBooks,
        maxSoloEnchantments = viewModel.maxSoloEnchantments,
        selectedMaxSoloEnchantments = viewModel.selectedMaxSoloEnchantments,
        addCustomBook = viewModel::addCustomBook,
        removeCustomBook = viewModel::removeCustomBook,
        toggleMaxSoloEnchantmentSelection = viewModel::toggleMaxSoloEnchantmentSelection,
        selectDefaults = viewModel::selectDefaults,
        resetSelection = viewModel::resetSelection,
    )
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ChooseBooks(
    navigateUp: () -> Unit,
    searchQuery: String,
    onSearchQueryChange: (newQuery: String) -> Unit,
    target: Item,
    customBooks: List<Item>,
    maxSoloEnchantments: List<Enchantment>,
    selectedMaxSoloEnchantments: List<Enchantment>,
    addCustomBook: (book: Item) -> Unit,
    removeCustomBook: (book: Item) -> Unit,
    toggleMaxSoloEnchantmentSelection: (Enchantment) -> Unit,
    selectDefaults: () -> Unit,
    resetSelection: () -> Unit,
) {
    ScreenWithLazyColumn(
        title = stringResource(R.string.choose_books),
        navigateUp = navigateUp,
        searchQuery = searchQuery,
        onSearchQueryChange = onSearchQueryChange,
    ) {
        item {
            Target(
                target = target,
                hasSelection = selectedMaxSoloEnchantments.isNotEmpty(),
                selectDefaults = selectDefaults,
                resetSelection = resetSelection,
                modifier = Modifier.padding(4.dp)
            )
        }
        item {
            Text(
                text = stringResource(R.string.custom_books),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(16.dp),
            )
        }
        itemsIndexed(
            items = customBooks,
            key = { _, book ->
                book.key
            },
        ) { index, book ->
            val topActive = index - 1 in customBooks.indices
            val bottomActive = index + 1 in customBooks.indices

            ImageTextCard(
                painterResourceId = R.drawable.book_enchanted,
                text = book.enchantments.displayString ?: stringResource(id = R.string.unenchanted),
                onClick = { removeCustomBook(book) },
                active = true,
                topActive = topActive,
                bottomActive = bottomActive,
                modifier = Modifier.fillMaxWidth(),
            )
        }
        item {
            ImageTextCard(
                imageVector = ThemeIcons.Add,
                text = "Add custom book",
                onClick = { /*TODO*/ },
                modifier = Modifier.fillMaxWidth(),
            )
        }
        item {
            Text(
                text = stringResource(R.string.max_solo_enchantments),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.padding(16.dp).animateItemPlacement(),
            )
        }
        itemsIndexed(
            items = maxSoloEnchantments,
            key = { _, enchantment ->
                enchantment.toString()
            }
        ) { index, enchantment ->
            val selected = enchantment in selectedMaxSoloEnchantments
            val topSelected =
                maxSoloEnchantments.getOrNull(index - 1) in selectedMaxSoloEnchantments
            val bottomSelected =
                maxSoloEnchantments.getOrNull(index + 1) in selectedMaxSoloEnchantments

            ImageTextCard(
                painterResourceId = R.drawable.book_enchanted,
                text = enchantment.toString(),
                active = selected,
                topActive = topSelected,
                bottomActive = bottomSelected,
                onClick = { toggleMaxSoloEnchantmentSelection(enchantment) },
                modifier = Modifier.fillMaxWidth().animateItemPlacement(),
            )
        }
    }
}

@Preview
@Composable
fun ChooseBooksPreview() {
    EnchantmentOrderTheme {

        val edition = Edition.Java
        val target = new(ItemType.Pickaxe, Enchantment(EnchantmentType.Fortune, 3))
        var searchQuery by rememberMutableStateOf("")
        var customBooks by rememberMutableStateOf(emptyList<Item>())
        var maxSoloEnchantments by rememberMutableStateOf(
            target.type.compatibleEnchantmentTypes
                .forEdition(edition)
                .map { enchantmentType -> max(enchantmentType) }
                .removeIncompatibleWith(target.enchantments)
        )
        var selectedMaxSoloEnchantments by rememberMutableStateOf(emptyList<Enchantment>())

        fun refreshMaxSoloEnchantments() {
            var supposedItem = target
            for (customBook in customBooks) {
                supposedItem = (supposedItem + customBook).product
            }
            for (maxSingleEnchantment in selectedMaxSoloEnchantments) {
                val book = enchantedBook(maxSingleEnchantment)
                supposedItem = (supposedItem + book).product
            }

            maxSoloEnchantments = target.type.compatibleEnchantmentTypes
                .forEdition(edition)
                .map { enchantmentType -> max(enchantmentType) }
                .removeIncompatibleWith(target.enchantments)
                .search(searchQuery) { it.toString() }
        }

        ChooseBooks(
            navigateUp = {},
            searchQuery = searchQuery,
            onSearchQueryChange = { newQuery ->
                searchQuery = newQuery
                refreshMaxSoloEnchantments()
            },
            target = target,
            customBooks = customBooks,
            maxSoloEnchantments = maxSoloEnchantments,
            selectedMaxSoloEnchantments = selectedMaxSoloEnchantments,
            addCustomBook = { book ->
                customBooks += book
                refreshMaxSoloEnchantments()
            },
            removeCustomBook = { book ->
                customBooks -= book
                refreshMaxSoloEnchantments()
            },
            toggleMaxSoloEnchantmentSelection = { enchantment ->
                if (enchantment in selectedMaxSoloEnchantments) {
                    selectedMaxSoloEnchantments += enchantment
                } else {
                    selectedMaxSoloEnchantments -= enchantment
                }
                refreshMaxSoloEnchantments()
            },
            selectDefaults = {
                var supposedItem = target
                for (customBook in customBooks) {
                    supposedItem = (supposedItem + customBook).product
                }

                selectedMaxSoloEnchantments = target.type
                    .getDefaultEnchantmentsForEdition(edition)
                    .removeIncompatibleWith(supposedItem.enchantments)
                refreshMaxSoloEnchantments()
            },
            resetSelection = {
                selectedMaxSoloEnchantments = emptyList()
                refreshMaxSoloEnchantments()
            },
        )

    }
}