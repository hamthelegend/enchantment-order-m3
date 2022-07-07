package com.hamthelegend.enchantmentorder.domain.model.item

import com.hamthelegend.enchantmentorder.domain.model.enchantment.Enchantment
import kotlin.random.Random

data class Item(
    val type: ItemType,
    val enchantments: List<Enchantment> = emptyList(),
    val anvilUseCount: Int = 0,
    val key: Double = Random.nextDouble(),
) {

    override fun toString() = "${type.friendlyName}: $enchantments"

}

fun ItemType.toNewItem(enchantments: List<Enchantment> = emptyList()) =
    Item(type = this, enchantments = enchantments, anvilUseCount = 0)

fun new(itemType: ItemType, vararg enchantments: Enchantment) =
    itemType.toNewItem(enchantments.toList())