package com.hamthelegend.enchantmentorder.android.ui.screens.choosebooks

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.hamthelegend.enchantmentorder.android.ui.screens.navArgs
import com.hamthelegend.enchantmentorder.domain.businesslogic.*
import com.hamthelegend.enchantmentorder.domain.exceptions.CombinationException
import com.hamthelegend.enchantmentorder.domain.extensions.*
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
            refreshList()
        }

    private var _customBooks by mutableStateOf(emptyList<Item>())
    var customBooks
        get() = _customBooks
        private set(value) {
            _customBooks = value
            refreshList()
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
            refreshList()
        }

    private fun refreshList() {
        val supposedProduct = (listOf(target) + customBooks).supposedProduct

        maxSoloEnchantments = target.type.compatibleEnchantmentTypes
            .forEdition(edition)
            .removeIncompatibleWith(selectedMaxSoloEnchantments.map { it.type })
            .map { enchantmentType -> max(enchantmentType) }
            .removeIncompatibleWith(supposedProduct?.enchantments ?: emptyList())
            .search(searchQuery) { it.toString() }
    }

    fun onSearchQueryChange(newQuery: String) {
        searchQuery = newQuery
    }

    fun addCustomBook(book: Item): Boolean {
        val supposedProduct = (
                listOf(target) +
                        customBooks +
                        selectedMaxSoloEnchantments.map { enchantedBook(it) }
                ).supposedProduct
        return try {
            supposedProduct!! + book
            customBooks += book
            true
        } catch (_: CombinationException) {
            false
        }
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
        val supposedProduct = (listOf(target) + customBooks).supposedProduct

        selectedMaxSoloEnchantments = target.type
            .getDefaultEnchantmentsForEdition(edition)
            .removeIncompatibleWith(supposedProduct?.enchantments ?: emptyList())
    }

    fun resetSelection() {
        selectedMaxSoloEnchantments = emptyList()
    }

}