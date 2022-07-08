package com.hamthelegend.enchantmentorder.domain

import com.hamthelegend.enchantmentorder.domain.models.combination.Combination
import com.hamthelegend.enchantmentorder.domain.models.enchantment.Enchantment
import com.hamthelegend.enchantmentorder.domain.models.item.Item
import com.hamthelegend.enchantmentorder.domain.models.item.ItemType

fun Combination.assert(
    cost: Int,
    itemType: ItemType,
    vararg enchantments: Enchantment,
) {
    println(this)
    assert(product.type == itemType)
    println(product.enchantments.toSet())
    println(enchantments.toSet())
    println(product.enchantments.toSet() == enchantments.toSet())
    assert(product.enchantments.toSet() == enchantments.toSet())
    assert(this.cost == cost)
}

inline fun <reified E: Exception> assert(combinationBlock: () -> Combination) {
    try {
        val combination = combinationBlock()
        print(combination)
        assert(false)
    } catch (e: Exception) {
        assert(e is E)
    }
}