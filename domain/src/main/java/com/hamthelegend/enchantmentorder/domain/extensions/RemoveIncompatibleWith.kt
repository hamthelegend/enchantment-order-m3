package com.hamthelegend.enchantmentorder.domain.extensions

import com.hamthelegend.enchantmentorder.domain.businesslogic.isCompatibleWith
import com.hamthelegend.enchantmentorder.domain.models.enchantment.Enchantment
import com.hamthelegend.enchantmentorder.domain.models.enchantment.EnchantmentType

fun List<EnchantmentType>.removeIncompatibleWith(selectedEnchantmentTypes: List<EnchantmentType>) =
    filter { enchantmentType ->
        selectedEnchantmentTypes.all { selectedEnchantmentType ->
            enchantmentType isCompatibleWith selectedEnchantmentType
        }
    }

@JvmName("removeIncompatibleWithEnchantments")
fun List<Enchantment>.removeIncompatibleWith(selectedEnchantment: List<Enchantment>) =
    filter { enchantment ->
        selectedEnchantment.all { selectedEnchantmentType ->
            enchantment isCompatibleWith selectedEnchantmentType
        }
    }