package com.hamthelegend.enchantmentorder.domain.extensions

import com.hamthelegend.enchantmentorder.domain.models.enchantment.Enchantment

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