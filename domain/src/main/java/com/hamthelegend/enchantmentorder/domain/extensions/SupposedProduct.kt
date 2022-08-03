package com.hamthelegend.enchantmentorder.domain.extensions

import com.hamthelegend.enchantmentorder.domain.businesslogic.combine
import com.hamthelegend.enchantmentorder.domain.models.edition.Edition
import com.hamthelegend.enchantmentorder.domain.models.item.Item

fun List<Item>.getSupposedProduct(edition: Edition): Item? {
    if (isEmpty()) return null
    val items = toMutableList()
    var supposedProduct = items.removeFirst()
    for (item in items) {
        supposedProduct = supposedProduct.combineWith(item, edition).product
    }
    return supposedProduct
}