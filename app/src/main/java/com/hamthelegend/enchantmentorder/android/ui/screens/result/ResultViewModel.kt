package com.hamthelegend.enchantmentorder.android.ui.screens.result

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hamthelegend.enchantmentorder.android.ui.screens.navArgs
import com.hamthelegend.enchantmentorder.domain.businesslogic.getBestOrder
import com.hamthelegend.enchantmentorder.domain.models.combination.CombinationOrder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ResultViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
): ViewModel() {

    val navArgs: ResultNavArgs = savedStateHandle.navArgs()

    private val edition = navArgs.edition

    private val target = navArgs.target

    private val books = navArgs.books.toList()

    var combinationOrder: CombinationOrder? by mutableStateOf(null)
        private set

    var compact by mutableStateOf(false)
        private set

    init {
        viewModelScope.launch {
            withContext(Dispatchers.Default) {
                val _combinationOrder = getBestOrder(target, books, edition)
                withContext(Dispatchers.Main) {
                    combinationOrder = _combinationOrder
                }
            }
        }
    }

    fun toggleCompact() {
        compact = !compact
    }

}