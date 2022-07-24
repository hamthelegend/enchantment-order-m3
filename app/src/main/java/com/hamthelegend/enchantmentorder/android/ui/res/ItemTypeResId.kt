package com.hamthelegend.enchantmentorder.android.ui.res

import androidx.annotation.DrawableRes
import androidx.annotation.IdRes
import com.hamthelegend.enchantmentorder.android.R
import com.hamthelegend.enchantmentorder.domain.models.item.ItemType

val ItemType.imageResId
    get(): @DrawableRes Int = when (this) {
        ItemType.EnchantedBook -> R.drawable.book_enchanted
        ItemType.Helmet -> R.drawable.helmet
        ItemType.Chestplate -> R.drawable.chestplate
        ItemType.Leggings -> R.drawable.leggings
        ItemType.Boots -> R.drawable.boots
        ItemType.Elytra -> R.drawable.elytra
        ItemType.Head -> R.drawable.head
        ItemType.Sword -> R.drawable.sword
        ItemType.Axe -> R.drawable.axe
        ItemType.Pickaxe -> R.drawable.pickaxe
        ItemType.Shovel -> R.drawable.shovel
        ItemType.Hoe -> R.drawable.hoe
        ItemType.Bow -> R.drawable.bow
        ItemType.FishingRod -> R.drawable.fishing_rod
        ItemType.Trident -> R.drawable.trident
        ItemType.Crossbow -> R.drawable.crossbow
        ItemType.Shears -> R.drawable.shears
        ItemType.FlintAndSteel -> R.drawable.flint_and_steel
        ItemType.CarrotOnAStick -> R.drawable.carrot_on_a_stick
        ItemType.WarpedFungusOnAStick -> R.drawable.warped_fungus_on_a_stick
    }