package com.hamthelegend.enchantmentorder.android.ui.screens.result

import com.hamthelegend.enchantmentorder.domain.models.edition.Edition
import com.hamthelegend.enchantmentorder.domain.models.item.Item

data class ResultNavArgs(
    val edition: Edition,
    val target: Item,
    val books: Array<Item>,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ResultNavArgs

        if (edition != other.edition) return false
        if (target != other.target) return false
        if (!books.contentEquals(other.books)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = edition.hashCode()
        result = 31 * result + target.hashCode()
        result = 31 * result + books.contentHashCode()
        return result
    }
}
