package com.hamthelegend.enchantmentorder.domain.businesslogic

import com.hamthelegend.enchantmentorder.domain.exceptions.IncompatibleItemTypesException
import com.hamthelegend.enchantmentorder.domain.exceptions.NoCompatibleEnchantmentsException
import com.hamthelegend.enchantmentorder.domain.models.enchantment.Enchantment
import com.hamthelegend.enchantmentorder.domain.models.enchantment.EnchantmentType
import com.hamthelegend.enchantmentorder.domain.models.item.Item
import com.hamthelegend.enchantmentorder.domain.models.item.ItemType

infix fun EnchantmentType.isCompatibleWith(targetEnchantmentType: EnchantmentType): Boolean {
    val sacrificeEnchantmentType = this
    return sacrificeEnchantmentType !in targetEnchantmentType.incompatibleEnchantmentTypes
}

infix fun EnchantmentType.isCompatibleWith(targetItemType: ItemType): Boolean {
    val sacrificeEnchantmentType = this
    return sacrificeEnchantmentType in targetItemType.compatibleEnchantmentTypes
}

infix fun EnchantmentType.isCompatibleWith(
    targetEnchantmentTypes: List<EnchantmentType>
): Boolean {
    val sacrificeEnchantmentType = this
    return targetEnchantmentTypes.all { targetEnchantmentType ->
        sacrificeEnchantmentType isCompatibleWith targetEnchantmentType
    }
}

infix fun Enchantment.isCompatibleWith(targetEnchantment: Enchantment) : Boolean {
    val sacrificeEnchantment = this
    return when (sacrificeEnchantment.type) {
        targetEnchantment.type -> when {
            sacrificeEnchantment.level < targetEnchantment.level -> false
            sacrificeEnchantment.level == targetEnchantment.level &&
                    sacrificeEnchantment.level >= sacrificeEnchantment.type.maxLevel -> false
            else -> true
        }
        else -> sacrificeEnchantment.type isCompatibleWith  targetEnchantment.type
    }
}

infix fun ItemType.isCompatibleWith(targetItemType: ItemType): Boolean {
    val sacrificeItemType = this
    return targetItemType == sacrificeItemType || sacrificeItemType == ItemType.EnchantedBook
}

infix fun Item.hasCompatibleEnchantmentsWith(target: Item): Boolean {
    val sacrifice = this
    return sacrifice.enchantments.any { sacrificeEnchantment ->
        val sacrificeEnchantmentCompatible = target.enchantments.all { targetEnchantment ->
            sacrificeEnchantment isCompatibleWith targetEnchantment
        }
        sacrificeEnchantmentCompatible &&
                sacrificeEnchantment.type isCompatibleWith target.type
    }
}

infix fun Item.checkIsCompatibleWith(target: Item) {
    val sacrifice = this
    sacrifice checkItemTypeCompatibilityWith target
    sacrifice checkEnchantmentsCompatibilityWith target
}

private infix fun Item.checkItemTypeCompatibilityWith(target: Item) {
    val sacrifice = this
    if (sacrifice.type isIncompatibleWith target.type) {
        throw IncompatibleItemTypesException(target.type, sacrifice.type)
    }
}

private infix fun Item.checkEnchantmentsCompatibilityWith(target: Item) {
    val sacrifice = this
    if (sacrifice hasNoCompatibleEnchantmentsWith target) {
        throw NoCompatibleEnchantmentsException(target, sacrifice)
    }
}