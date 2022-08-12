package com.hamthelegend.enchantmentorder.domain.businesslogic

import com.hamthelegend.enchantmentorder.domain.exceptions.CombinationException
import com.hamthelegend.enchantmentorder.domain.extensions.combineWith
import com.hamthelegend.enchantmentorder.domain.extensions.enchantedBook
import com.hamthelegend.enchantmentorder.domain.models.combination.Combination
import com.hamthelegend.enchantmentorder.domain.models.combination.CombinationOrder
import com.hamthelegend.enchantmentorder.domain.models.edition.Edition
import com.hamthelegend.enchantmentorder.domain.models.enchantment.Enchantment
import com.hamthelegend.enchantmentorder.domain.models.item.Item
import com.hamthelegend.enchantmentorder.extensions.nextOrNull
import java.util.*

val tooManyBooks = 9

/*
 This algorithm does not get the actual best order if the target item has prior penalty.
 TODO: This must be updated to include the target in the permutations.
 */

@JvmName("getBestOrderByEnchantments")
fun getBestOrder(
    target: Item,
    enchantments: List<Enchantment>,
    edition: Edition,
): CombinationOrder {
    val items = enchantments.map { enchantment -> enchantedBook(enchantment) }
    return getBestOrder(target, items, edition)
}

fun getBestOrder(
    target: Item,
    items: List<Item>,
    edition: Edition,
): CombinationOrder {
    var bestCombinationOrder: CombinationOrder? = null

    fun generatePermutation(items: MutableList<Item>, k: Int) {
        // If only 1 element, just output the array
        if (k == 1) {
            try {
                val currentBestCombinationOrder = bestCombinationOrder
                val combinationOrder = listOf(target, *items.toTypedArray()).combine(edition)
                if (currentBestCombinationOrder == null) {
                    bestCombinationOrder = combinationOrder
                } else if (combinationOrder.totalCost < currentBestCombinationOrder.totalCost) {
                    bestCombinationOrder = combinationOrder
                }
            } catch (_: CombinationException) {
            }
        } else {
            for (i in 0 until k) {
                generatePermutation(items, k - 1)
                if (k % 2 == 0) {
                    Collections.swap(items, i, k - 1)
                } else {
                    Collections.swap(items, 0, k - 1)
                }
            }
        }
    }

    generatePermutation(items.toMutableList(), items.size)
    return bestCombinationOrder ?: throw CombinationException("There is an incompatible book")
}

fun List<Item>.combine(edition: Edition): CombinationOrder {
    val combinations = mutableListOf<Combination>()
    var items = this
    var previousItems: List<Item>? = null
    while (items.size > 1) {
        val nextPreviousItems = items
        val currentItems = items.toMutableList()
        val nextItems = mutableListOf<Item>()
        while (currentItems.isNotEmpty()) {
            val target = currentItems.removeFirst()
            val sacrifice = currentItems.removeFirstOrNull()
            if (sacrifice == null) {
                nextItems.add(target)
            } else {
                if (
                    target.anvilUseCount == sacrifice.anvilUseCount ||
                            previousItems == items
                ) {
                    val combination = target.combineWith(sacrifice, edition)
                    combinations.add(combination)
                    nextItems.add(combination.product)
                } else {
                    nextItems.add(target)
                    currentItems.add(0, sacrifice)
                }
            }
        }
        items = nextItems
        previousItems = nextPreviousItems
    }
    return CombinationOrder(combinations)
}