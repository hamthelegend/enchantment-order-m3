package com.hamthelegend.enchantmentorder.domain.businesslogic

import com.hamthelegend.enchantmentorder.domain.models.enchantment.Enchantment
import com.hamthelegend.enchantmentorder.domain.models.enchantment.EnchantmentType
import com.hamthelegend.enchantmentorder.domain.models.item.Item
import com.hamthelegend.enchantmentorder.domain.models.item.ItemType

infix fun EnchantmentType.isIncompatibleWith(targetEnchantmentType: EnchantmentType) =
    !isCompatibleWith(targetEnchantmentType)

infix fun EnchantmentType.isIncompatibleWith(targetItemType: ItemType) =
    !isCompatibleWith(targetItemType)

infix fun EnchantmentType.isIncompatibleWith(targetEnchantmentTypes: List<EnchantmentType>) =
    !isCompatibleWith(targetEnchantmentTypes)

infix fun Enchantment.isIncompatibleWith(targetEnchantment: Enchantment) =
    !isCompatibleWith(targetEnchantment)

infix fun ItemType.isIncompatibleWith(targetItemType: ItemType) =
    !isCompatibleWith(targetItemType)

infix fun Item.hasNoCompatibleEnchantmentsWith(target: Item) =
    !hasCompatibleEnchantmentsWith(target)