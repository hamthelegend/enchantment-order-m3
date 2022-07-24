package com.hamthelegend.enchantmentorder.android.ui.screens.addinitialenchantments

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.hamthelegend.enchantmentorder.android.ui.screens.navArgs
import com.hamthelegend.enchantmentorder.domain.businesslogic.forEdition
import com.hamthelegend.enchantmentorder.domain.businesslogic.getDefaultEnchantmentsForEdition
import com.hamthelegend.enchantmentorder.domain.businesslogic.removedIncompatibleWith
import com.hamthelegend.enchantmentorder.domain.models.enchantment.Enchantment
import com.hamthelegend.enchantmentorder.domain.models.enchantment.EnchantmentType
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

    var searchQuery by mutableStateOf("")
        private set

    var enchantmentTypes by mutableStateOf(target.compatibleEnchantmentTypes.forEdition(edition))
        private set

    var initialEnchantments: List<Enchantment> by mutableStateOf(emptyList())
        private set

    private fun refreshEnchantmentTypes() {
        enchantmentTypes = target.compatibleEnchantmentTypes
            .forEdition(edition)
            .search(searchQuery) { it.friendlyName }
            .removedIncompatibleWith(initialEnchantments.map { it.type })
    }

    fun onSearchQueryChange(newQuery: String) {
        searchQuery = newQuery
        refreshEnchantmentTypes()
    }

    fun addInitialEnchantment(enchantment: Enchantment) {
        initialEnchantments += enchantment
        refreshEnchantmentTypes()
    }

    fun removeInitialEnchantment(enchantment: Enchantment) {
        initialEnchantments -= enchantment
        refreshEnchantmentTypes()
    }

    fun selectDefaults() {
        initialEnchantments = target.getDefaultEnchantmentsForEdition(edition)
        refreshEnchantmentTypes()
    }

    fun resetSelection() {
        initialEnchantments = emptyList()
        refreshEnchantmentTypes()
    }

}