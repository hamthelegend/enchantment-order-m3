package com.hamthelegend.enchantmentorder.android.ui.screens.choosebooks

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.twotone.Add
import androidx.compose.material.icons.twotone.Done
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hamthelegend.enchantmentorder.android.R
import com.hamthelegend.enchantmentorder.android.ui.common.InfoCard
import com.hamthelegend.enchantmentorder.android.ui.common.Target
import com.hamthelegend.enchantmentorder.android.ui.screen.LazyColumnScreen
import com.hamthelegend.enchantmentorder.android.ui.screens.destinations.AddCustomBookScreenDestination
import com.hamthelegend.enchantmentorder.android.ui.screens.destinations.ResultScreenDestination
import com.hamthelegend.enchantmentorder.android.ui.theme.EnchantmentOrderTheme
import com.hamthelegend.enchantmentorder.android.ui.theme.ThemeIcons
import com.hamthelegend.enchantmentorder.composables.FloatingActionButton
import com.hamthelegend.enchantmentorder.composables.IconTextCard
import com.hamthelegend.enchantmentorder.composables.rememberMutableStateOf
import com.hamthelegend.enchantmentorder.domain.extensions.*
import com.hamthelegend.enchantmentorder.domain.models.edition.Edition
import com.hamthelegend.enchantmentorder.domain.models.enchantment.Enchantment
import com.hamthelegend.enchantmentorder.domain.models.enchantment.EnchantmentType
import com.hamthelegend.enchantmentorder.domain.models.item.Item
import com.hamthelegend.enchantmentorder.domain.models.item.ItemType
import com.hamthelegend.enchantmentorder.extensions.search
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@ChooseBooksNavGraph(start = true)
@Destination(navArgsDelegate = ChooseBooksNavArgs::class)
@Composable
fun ChooseBooksScreen(
    navigator: DestinationsNavigator,
    viewModel: ChooseBooksViewModel,
) {
    ChooseBooks(
        navigateUp = navigator::navigateUp,
        searchQuery = viewModel.searchQuery,
        onSearchQueryChange = viewModel::onSearchQueryChange,
        target = viewModel.target,
        customBooks = viewModel.customBooks,
        maxSoloEnchantments = viewModel.maxSoloEnchantments,
        selectedMaxSoloEnchantments = viewModel.selectedMaxSoloEnchantments,
        navigateToAddCustomBookScreen = {
            navigator.navigate(
                AddCustomBookScreenDestination(
                    edition = viewModel.edition,
                    target = viewModel.target,
                )
            )
        },
        removeCustomBook = viewModel::removeCustomBook,
        toggleMaxSoloEnchantmentSelection = viewModel::toggleMaxSoloEnchantmentSelection,
        selectDefaults = viewModel::selectDefaults,
        resetSelection = viewModel::resetSelection,
        navigateToResultScreen = {
            navigator.navigate(ResultScreenDestination(
                edition = viewModel.edition,
                target = viewModel.target,
                books = viewModel.books.toTypedArray(),
            ))
        },
    )
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalAnimationApi::class)
@Composable
fun ChooseBooks(
    navigateUp: () -> Unit,
    searchQuery: String,
    onSearchQueryChange: (newQuery: String) -> Unit,
    target: Item,
    customBooks: List<Item>,
    maxSoloEnchantments: List<Enchantment>,
    selectedMaxSoloEnchantments: List<Enchantment>,
    navigateToAddCustomBookScreen: () -> Unit,
    removeCustomBook: (book: Item) -> Unit,
    toggleMaxSoloEnchantmentSelection: (Enchantment) -> Unit,
    selectDefaults: () -> Unit,
    resetSelection: () -> Unit,
    navigateToResultScreen: () -> Unit,
) {
    LazyColumnScreen(
        title = stringResource(R.string.choose_books),
        navigateUp = navigateUp,
        searchQuery = searchQuery,
        onSearchQueryChange = onSearchQueryChange,
        floatingActionButton = {
            val fabVisible =
                selectedMaxSoloEnchantments.isNotEmpty() || customBooks.isNotEmpty()

            AnimatedVisibility(
                visible = fabVisible,
                enter = scaleIn(),
                exit = scaleOut(),
            ) {
                FloatingActionButton(
                    onClick = navigateToResultScreen,
                    imageVector = ThemeIcons.Done,
                    contentDescription = stringResource(id = R.string.done),
                    modifier = Modifier.navigationBarsPadding(),
                )
            }
        }
    ) {
        item {
            InfoCard(
                text = stringResource(
                    R.string.choose_books_info,
                    target.type.friendlyName.lowercase(),
                ),
                modifier = Modifier.fillMaxWidth().padding(4.dp),
            )
        }
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
                modifier = Modifier
                    .padding(16.dp)
                    .animateItemPlacement(),
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

            IconTextCard(
                painterResourceId = R.drawable.book,
                text = book.enchantments.displayString ?: stringResource(id = R.string.unenchanted),
                onClick = { removeCustomBook(book) },
                active = true,
                topActive = topActive,
                bottomActive = bottomActive,
                modifier = Modifier
                    .fillMaxWidth()
                    .animateItemPlacement(),
            )
        }
        item {
            IconTextCard(
                imageVector = ThemeIcons.Add,
                text = stringResource(R.string.add_custom_book),
                onClick = navigateToAddCustomBookScreen,
                modifier = Modifier
                    .fillMaxWidth()
                    .animateItemPlacement(),
            )
        }
        item {
            Text(
                text = stringResource(R.string.max_solo_enchantments),
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier
                    .padding(16.dp)
                    .animateItemPlacement(),
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

            IconTextCard(
                painterResourceId = R.drawable.book,
                text = enchantment.toString(),
                active = selected,
                topActive = topSelected,
                bottomActive = bottomSelected,
                onClick = { toggleMaxSoloEnchantmentSelection(enchantment) },
                modifier = Modifier
                    .fillMaxWidth()
                    .animateItemPlacement(),
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
        var selectedMaxSoloEnchantments by rememberMutableStateOf(emptyList<Enchantment>())
        var supposedProduct = target
        for (customBook in customBooks) {
            supposedProduct = supposedProduct.combineWith(customBook, edition).product
        }
        val maxSoloEnchantments = target.type.compatibleEnchantmentTypes
            .forEdition(edition)
            .removeIncompatibleWith(selectedMaxSoloEnchantments.map { it.type })
            .map { enchantmentType -> max(enchantmentType) }
            .removeIncompatibleWith(supposedProduct.enchantments)
            .search(searchQuery) { it.toString() }

        ChooseBooks(
            navigateUp = {},
            searchQuery = searchQuery,
            onSearchQueryChange = { newQuery ->
                searchQuery = newQuery
            },
            target = target,
            customBooks = customBooks,
            maxSoloEnchantments = maxSoloEnchantments,
            selectedMaxSoloEnchantments = selectedMaxSoloEnchantments,
            removeCustomBook = { book ->
                customBooks -= book
            },
            toggleMaxSoloEnchantmentSelection = { enchantment ->
                if (enchantment in selectedMaxSoloEnchantments) {
                    selectedMaxSoloEnchantments += enchantment
                } else {
                    selectedMaxSoloEnchantments -= enchantment
                }
            },
            selectDefaults = {
                var _supposedItem = target
                for (customBook in customBooks) {
                    _supposedItem = _supposedItem.combineWith(customBook, edition).product
                }

                selectedMaxSoloEnchantments = target.type
                    .getDefaultEnchantmentsForEdition(edition)
                    .removeIncompatibleWith(_supposedItem.enchantments)
            },
            resetSelection = {
                selectedMaxSoloEnchantments = emptyList()
            },
            navigateToResultScreen = {},
            navigateToAddCustomBookScreen = {},
        )

    }
}