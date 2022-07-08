package com.hamthelegend.enchantmentorder.domain.businesslogic

import com.hamthelegend.enchantmentorder.domain.models.enchantment.Enchantment
import com.hamthelegend.enchantmentorder.domain.models.enchantment.EnchantmentType
import com.hamthelegend.enchantmentorder.domain.models.item.Item
import com.hamthelegend.enchantmentorder.domain.models.item.ItemType

fun EnchantmentType.toMaxEnchantment() = Enchantment(this, maxLevel)

fun max(enchantmentType: EnchantmentType) = enchantmentType.toMaxEnchantment()

fun ItemType.toNewItem(vararg enchantments: Enchantment) =
    Item(type = this, enchantments = enchantments.toSet(), anvilUseCount = 0)

fun new(itemType: ItemType, vararg enchantments: Enchantment) =
    itemType.toNewItem(*enchantments)

val targetableItemTypes = ItemType.values().toList() - ItemType.EnchantedBook