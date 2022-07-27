package com.hamthelegend.enchantmentorder.domain.extensions

import com.hamthelegend.enchantmentorder.domain.models.edition.Edition
import com.hamthelegend.enchantmentorder.domain.models.enchantment.EnchantmentType
import com.hamthelegend.enchantmentorder.domain.models.item.ItemType

val Collection<EnchantmentType>.forJava
    get() = filter { it.inJava }

val Collection<EnchantmentType>.forBedrock
    get() = filter { it.inBedrock }

fun Collection<EnchantmentType>.forEdition(edition: Edition) =
    when (edition) {
        Edition.Java -> forJava
        Edition.Bedrock -> forBedrock
    }

fun Array<EnchantmentType>.forEdition(edition: Edition) =
    when (edition) {
        Edition.Java -> filter { it.inJava }
        Edition.Bedrock -> filter { it.inBedrock }
    }

fun ItemType.getDefaultEnchantmentsForEdition(edition: Edition) =
    defaultEnchantmentTypes.forEdition(edition).map { enchantmentType -> max(enchantmentType) }