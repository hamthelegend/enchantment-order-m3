package com.hamthelegend.enchantmentorder.android.ui.screens.addinitialenchantments

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.hamthelegend.enchantmentorder.android.ui.screens.navArgs
import com.hamthelegend.enchantmentorder.domain.extensions.forEdition
import com.hamthelegend.enchantmentorder.domain.extensions.getDefaultEnchantmentsForEdition
import com.hamthelegend.enchantmentorder.domain.extensions.removeIncompatibleWith
import com.hamthelegend.enchantmentorder.domain.models.enchantment.Enchantment
import com.hamthelegend.enchantmentorder.extensions.search
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddInitialEnchantmentsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
): ViewModel() {

    private val navArgs: AddInitialEnchantmentsNavArgs = savedStateHandle.navArgs()

    val edition = navArgs.edition

    val target = navArgs.target

    private var _searchQuery by mutableStateOf("")
    var searchQuery
        get() = _searchQuery
        private set(value) {
            _searchQuery = value
            refreshList()
        }

    var enchantmentTypes by mutableStateOf(target.compatibleEnchantmentTypes.forEdition(edition))
        private set

    private var _initialEnchantments by mutableStateOf(emptyList<Enchantment>())
    var initialEnchantments
        get() = _initialEnchantments
        private set(value) {
            _initialEnchantments = value
            refreshList()
        }

    var renamingCostDialogVisible by mutableStateOf(false)
        private set

    private fun refreshList() {
        enchantmentTypes = target.compatibleEnchantmentTypes
            .forEdition(edition)
            .search(searchQuery) { it.friendlyName }
            .removeIncompatibleWith(initialEnchantments.map { it.type })
    }

    fun onSearchQueryChange(newQuery: String) {
        searchQuery = newQuery
    }

    fun addInitialEnchantment(enchantment: Enchantment) {
        initialEnchantments += enchantment
    }

    fun removeInitialEnchantment(enchantment: Enchantment) {
        initialEnchantments -= enchantment
    }

    fun selectDefaults() {
        initialEnchantments = target.getDefaultEnchantmentsForEdition(edition)
    }

    fun resetSelection() {
        initialEnchantments = emptyList()
    }

    fun showRenamingCostDialog() {
        renamingCostDialogVisible = true
    }

    fun hideRenamingCostDialog() {
        renamingCostDialogVisible = false
    }

}