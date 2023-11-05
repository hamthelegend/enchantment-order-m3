package com.hamthelegend.enchantmentorder.domain.extensions

import com.hamthelegend.enchantmentorder.domain.models.edition.Edition
import com.hamthelegend.enchantmentorder.domain.models.item.Item

// TODO: You need to get rid of this
internal operator fun Item.plus(other: Item) = combineWith(other, edition = Edition.Java)