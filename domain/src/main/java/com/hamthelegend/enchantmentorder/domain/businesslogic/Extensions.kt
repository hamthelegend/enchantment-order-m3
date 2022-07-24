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
    Item(type = this, enchantments = enchantments.toList(), anvilUseCount = 0)

fun new(itemType: ItemType, vararg enchantments: Enchantment) =
    itemType.toNewItem(*enchantments)

operator fun Item.plus(other: Item) = combine(this, other, Edition.Java)

fun Int.anvilUseCountToCost() = (2.0.pow(this) - 1).toInt()

fun Int.costToAnvilUseCount() = (ln(this.toDouble() + 1) / ln(2.0)).toInt()

fun Int.renameCostToAnvilUseCount() = (this - 1).costToAnvilUseCount()

fun Int.isCostTooExpensive() = this >= 40

fun enchantedBook(vararg enchantments: Enchantment) =
    Item(ItemType.EnchantedBook, enchantments.toList())

val targetableItemTypes = ItemType.values().toList() - ItemType.EnchantedBook

val Collection<EnchantmentType>.forJava
    get() = filter { it.inJava }

val Collection<EnchantmentType>.forBedrock
    get() = filter { it.inBedrock }

fun Collection<EnchantmentType>.forEdition(edition: Edition) =
    when (edition) {
        Edition.Java -> forJava
        Edition.Bedrock -> forBedrock
    }

val Collection<Enchantment>.displayString: String?
    get() {
        if (size == 0) return null
        if (size == 1) return first().arabicLevelString
        val stringBuilder = StringBuilder()
        for ((index, enchantment) in this.withIndex()) {
            if (index != 0) {
                stringBuilder.append(", ")
            }
            stringBuilder.append(enchantment.abbreviatedString)
        }
        return stringBuilder.toString()
    }

fun List<EnchantmentType>.removedIncompatibleWith(selectedEnchantmentTypes: List<EnchantmentType>) =
    filter { enchantmentType ->
        selectedEnchantmentTypes.all { selectedEnchantmentType ->
            enchantmentType isCompatibleWith selectedEnchantmentType
        }
    }

fun ItemType.getDefaultEnchantmentsForEdition(edition: Edition) =
    defaultEnchantmentTypes.forEdition(edition).map { enchantmentType ->
        Enchantment(enchantmentType, enchantmentType.maxLevel)
    }