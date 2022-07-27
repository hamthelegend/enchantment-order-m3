package com.hamthelegend.enchantmentorder.domain.extensions

import com.hamthelegend.enchantmentorder.domain.models.item.Item

val List<Item>.supposedProduct: Item?
    get() {
        if (isEmpty()) return null
        val items = toMutableList()
        var supposedProduct = items.removeFirst()
        for (item in items) {
            supposedProduct = (supposedProduct + item).product
        }
        return supposedProduct
    }