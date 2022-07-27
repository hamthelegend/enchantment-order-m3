package com.hamthelegend.enchantmentorder.domain.extensions

import com.hamthelegend.enchantmentorder.domain.businesslogic.combine
import com.hamthelegend.enchantmentorder.domain.models.edition.Edition
import com.hamthelegend.enchantmentorder.domain.models.item.Item

operator fun Item.plus(other: Item) = combine(this, other, Edition.Java)
