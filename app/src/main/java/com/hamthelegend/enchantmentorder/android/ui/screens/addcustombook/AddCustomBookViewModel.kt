package com.hamthelegend.enchantmentorder.android.ui.screens.addcustombook

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.hamthelegend.enchantmentorder.android.ui.screens.navArgs
import com.hamthelegend.enchantmentorder.domain.extensions.*
import com.hamthelegend.enchantmentorder.domain.models.enchantment.Enchantment
import com.hamthelegend.enchantmentorder.domain.models.enchantment.EnchantmentType
import com.hamthelegend.enchantmentorder.domain.models.item.Item
import com.hamthelegend.enchantmentorder.extensions.search
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddCustomBookViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
): ViewModel() {

    private val navArgs: AddCustomBookNavArgs = savedStateHandle.navArgs()

    val edition = navArgs.edition

    val target = navArgs.target

    val addCustomBook = navArgs.addCustomBook

    private var _searchQuery by mutableStateOf("")
    var searchQuery
        get() = _searchQuery
        set(value) {
            _searchQuery = value
            refreshList()
        }

    var enchantmentTypes by mutableStateOf(EnchantmentType.values().forEdition(edition))

    private var _bookEnchantments by mutableStateOf(listOf<Enchantment>())
    var bookEnchantments
        get() = _bookEnchantments
        private set(value) {
            _bookEnchantments = value
            refreshList()
        }

    var renamingCostDialogVisible by mutableStateOf(false)
        private set

    private fun refreshList() {
        enchantmentTypes = EnchantmentType.values()
            .forEdition(edition)
            .removeIncompatibleWith(bookEnchantments.map { it.type })
            .search(searchQuery) { it.friendlyName }
    }

    fun onSearchQueryChange(newQuery: String) {
        searchQuery = newQuery
    }

    fun addBookEnchantment(enchantment: Enchantment) {
        bookEnchantments += enchantment
    }

    fun removeBookEnchantment(enchantment: Enchantment) {
        bookEnchantments -= enchantment
    }

    fun selectDefaults() {
        bookEnchantments = target.type.getDefaultEnchantmentsForEdition(edition)
    }

    fun resetSelection() {
        bookEnchantments = emptyList()
    }

    fun addCustomBook(renamingCost: Int): Boolean {
        val book = enchantedBook(
            *bookEnchantments.toTypedArray(),
            anvilUseCount = renamingCost.renamingCostToAnvilUseCount(),
        )
        return addCustomBook.function(book)
    }

    fun showRenamingCostDialog() {
        renamingCostDialogVisible = true
    }

    fun hideRenamingCostDialog() {
        renamingCostDialogVisible = false
    }

}