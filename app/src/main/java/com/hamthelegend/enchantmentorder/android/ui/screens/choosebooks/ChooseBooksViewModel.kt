package com.hamthelegend.enchantmentorder.android.ui.screens.choosebooks

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.hamthelegend.enchantmentorder.android.ui.screens.navArgs
import com.hamthelegend.enchantmentorder.domain.businesslogic.*
import com.hamthelegend.enchantmentorder.domain.models.enchantment.Enchantment
import com.hamthelegend.enchantmentorder.domain.models.item.Item
import com.hamthelegend.enchantmentorder.extensions.search
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChooseBooksViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val navArgs: ChooseBooksNavArgs = savedStateHandle.navArgs()

    val edition = navArgs.edition

    val target = navArgs.target

    private var _searchQuery by mutableStateOf("")
    var searchQuery
        get() = _searchQuery
        private set(value) {
            _searchQuery = value
            refreshUi()
        }

    private var _customBooks by mutableStateOf(emptyList<Item>())
    var customBooks
        get() = _customBooks
        private set(value) {
            _customBooks = value
            refreshUi()
        }

    var maxSoloEnchantments by mutableStateOf(
        target.type.compatibleEnchantmentTypes
            .forEdition(edition)
            .map { enchantmentType -> max(enchantmentType) }
            .removeIncompatibleWith(target.enchantments)
    )

    private var _selectedMaxSoloEnchantments by mutableStateOf(emptyList<Enchantment>())
    var selectedMaxSoloEnchantments
        get() = _selectedMaxSoloEnchantments
        private set(value) {
            _selectedMaxSoloEnchantments = value
            refreshUi()
        }

    var navigateToResultScreenFabVisible by mutableStateOf(false)
        private set

    private fun refreshUi() {
        var supposedItem = target
        for (customBook in customBooks) {
            supposedItem = (supposedItem + customBook).product
        }

        maxSoloEnchantments = target.type.compatibleEnchantmentTypes
            .forEdition(edition)
            .removeIncompatibleWith(selectedMaxSoloEnchantments.map { it.type })
            .map { enchantmentType -> max(enchantmentType) }
            .removeIncompatibleWith(supposedItem.enchantments)
            .search(searchQuery) { it.toString() }

        navigateToResultScreenFabVisible =
            selectedMaxSoloEnchantments.isNotEmpty() || customBooks.isNotEmpty()
    }

    fun onSearchQueryChange(newQuery: String) {
        searchQuery = newQuery
    }

    fun addCustomBook(book: Item) {
        customBooks += book
    }

    fun removeCustomBook(book: Item) {
        customBooks -= book
    }

    fun toggleMaxSoloEnchantmentSelection(enchantment: Enchantment) {
        if (enchantment in selectedMaxSoloEnchantments) {
            selectedMaxSoloEnchantments -= enchantment
        } else {
            selectedMaxSoloEnchantments += enchantment
        }
    }

    fun selectDefaults() {
        var supposedItem = target
        for (customBook in customBooks) {
            supposedItem = (supposedItem + customBook).product
        }

        selectedMaxSoloEnchantments = target.type
            .getDefaultEnchantmentsForEdition(edition)
            .removeIncompatibleWith(supposedItem.enchantments)
    }

    fun resetSelection() {
        selectedMaxSoloEnchantments = emptyList()
    }

}