package com.hamthelegend.enchantmentorder.android.ui.screens.choosetarget

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.hamthelegend.enchantmentorder.android.ui.screens.navArgs
import com.hamthelegend.enchantmentorder.domain.businesslogic.targetableItemTypes
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChooseTargetViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
): ViewModel() {

    private val args: ChooseTargetNavArgs = savedStateHandle.navArgs()

    val edition = args.edition

    var searchQuery by mutableStateOf("")
        private set

    var availableTargets by mutableStateOf(targetableItemTypes)
        private set

    fun onSearchQueryChange(newQuery: String) {
        searchQuery = newQuery
        availableTargets = targetableItemTypes.filter { itemType ->
            newQuery in itemType.friendlyName
        }
    }

}