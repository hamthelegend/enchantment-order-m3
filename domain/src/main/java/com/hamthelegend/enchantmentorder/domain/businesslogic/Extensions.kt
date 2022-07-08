package com.hamthelegend.enchantmentorder.domain.businesslogic

import com.hamthelegend.enchantmentorder.domain.models.edition.Edition
import com.hamthelegend.enchantmentorder.domain.models.enchantment.Enchantment
import com.hamthelegend.enchantmentorder.domain.models.enchantment.EnchantmentType
import com.hamthelegend.enchantmentorder.domain.models.item.Item
import com.hamthelegend.enchantmentorder.domain.models.item.ItemType
import kotlin.math.ln
import kotlin.math.pow

fun EnchantmentType.toMaxEnchantment() = Enchantment(this, maxLevel)

fun max(enchantmentType: EnchantmentType) = enchantmentType.toMaxEnchantment()

fun ItemType.toNewItem(vararg enchantments: Enchantment) =
    Item(type = this, enchantments = enchantments.toSet(), anvilUseCount = 0)

fun new(itemType: ItemType, vararg enchantments: Enchantment) =
    itemType.toNewItem(*enchantments)

val targetableItemTypes = ItemType.values().toList() - ItemType.EnchantedBook

operator fun Item.plus(other: Item) = combine(this, other, Edition.Java)

fun Int.anvilUseCountToCost() = (2.0.pow(this) - 1).toInt()

fun Int.costToAnvilUseCount() = (ln(this.toDouble() + 1) / ln(2.0)).toInt()

fun Int.renameCostToAnvilUseCount() = (this - 1).costToAnvilUseCount()

fun Int.isCostTooExpensive() = this >= 40

fun enchantedBook(vararg enchantments: Enchantment) =
    Item(ItemType.EnchantedBook, enchantments.toSet())