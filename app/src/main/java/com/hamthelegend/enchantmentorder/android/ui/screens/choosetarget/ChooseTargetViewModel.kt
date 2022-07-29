package com.hamthelegend.enchantmentorder.android.ui.screens.choosetarget

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.hamthelegend.enchantmentorder.android.ui.screens.navArgs
import com.hamthelegend.enchantmentorder.domain.extensions.targetableItemTypes
import com.hamthelegend.enchantmentorder.extensions.search
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChooseTargetViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
): ViewModel() {

    private val navArgs = savedStateHandle.navArgs<ChooseTargetNavArgs>()

    val edition = navArgs.edition

    private var _searchQuery by mutableStateOf("")
    var searchQuery
        get() = _searchQuery
        private set(value) {
            _searchQuery = value
            refreshList()
        }

    var targets by mutableStateOf(targetableItemTypes)
        private set

    private fun refreshList() {
        targets = targetableItemTypes.search(searchQuery) { it.friendlyName }
    }

    fun onSearchQueryChange(newQuery: String) {
        searchQuery = newQuery
    }

}