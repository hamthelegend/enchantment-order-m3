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

    var searchQuery by mutableStateOf("")
        private set

    var customBooks by mutableStateOf(emptyList<Item>())

    var maxSoloEnchantments by mutableStateOf(
        target.type.compatibleEnchantmentTypes
            .forEdition(edition)
            .map { enchantmentType -> max(enchantmentType) }
            .removeIncompatibleWith(target.enchantments)
    )

    var selectedMaxSoloEnchantments by mutableStateOf(emptyList<Enchantment>())

    private fun refreshMaxSoloEnchantments() {
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

    fun onSearchQueryChange(newQuery: String) {
        searchQuery = newQuery
        refreshMaxSoloEnchantments()
    }

    fun addCustomBook(book: Item) {
        customBooks += book
        refreshMaxSoloEnchantments()
    }

    fun removeCustomBook(book: Item) {
        customBooks -= book
        refreshMaxSoloEnchantments()
    }

    fun toggleMaxSoloEnchantmentSelection(enchantment: Enchantment) {
        if (enchantment in selectedMaxSoloEnchantments) {
            selectedMaxSoloEnchantments += enchantment
        } else {
            selectedMaxSoloEnchantments -= enchantment
        }
        refreshMaxSoloEnchantments()
    }

    fun selectDefaults() {
        var supposedItem = target
        for (customBook in customBooks) {
            supposedItem = (supposedItem + customBook).product
        }

        selectedMaxSoloEnchantments = target.type
            .getDefaultEnchantmentsForEdition(edition)
            .removeIncompatibleWith(supposedItem.enchantments)
        refreshMaxSoloEnchantments()
    }

    fun resetSelection() {
        selectedMaxSoloEnchantments = emptyList()
        refreshMaxSoloEnchantments()
    }

}