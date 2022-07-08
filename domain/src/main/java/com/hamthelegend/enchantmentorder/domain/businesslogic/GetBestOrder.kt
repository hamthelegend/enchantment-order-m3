package com.hamthelegend.enchantmentorder.domain.businesslogic

import com.hamthelegend.enchantmentorder.domain.exceptions.CombinationException
import com.hamthelegend.enchantmentorder.domain.models.combination.Combination
import com.hamthelegend.enchantmentorder.domain.models.combination.CombinationOrder
import com.hamthelegend.enchantmentorder.domain.models.enchantment.Enchantment
import com.hamthelegend.enchantmentorder.domain.models.item.Item
import com.hamthelegend.enchantmentorder.domain.models.item.ItemType
import java.util.*

val tooManyBooks = 9

@JvmName("getBestOrderByEnchantments")
fun getBestOrder(
    target: Item,
    enchantments: List<Enchantment>
): CombinationOrder {
    val items = enchantments.map { enchantment -> Item(ItemType.EnchantedBook, setOf(enchantment)) }
    return getBestOrder(target, items)
}

fun getBestOrder(target: Item, items: List<Item>): CombinationOrder {
    var bestCombinationOrder: CombinationOrder? = null

    fun generatePermutation(items: MutableList<Item>, k: Int) {
        // If only 1 element, just output the array
        if (k == 1) {
            try {
                val currentBestCombinationOrder = bestCombinationOrder
                val combinationOrder = listOf(target, *items.toTypedArray()).combine()
                if (currentBestCombinationOrder == null) {
                    bestCombinationOrder = combinationOrder
                } else if (combinationOrder.totalCost < currentBestCombinationOrder.totalCost) {
                    bestCombinationOrder = combinationOrder
                }
            } catch (_: CombinationException) {  }
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

fun List<Item>.combine(): CombinationOrder {
    val combinations = mutableListOf<Combination>()
    var currentItems = this
    while (currentItems.size > 1) {
        val nextItems = mutableListOf<Item>()
        for (index in currentItems.indices step 2) {
            if (index + 1 <= currentItems.lastIndex) {
                val target = currentItems[index]
                val sacrifice = currentItems[index + 1]
                val combinationResult = target + sacrifice
                combinations.add(combinationResult)
                nextItems.add(combinationResult.product)
            } else nextItems.add(currentItems[index])
        }
        currentItems = nextItems
    }
    return CombinationOrder(combinations)
}