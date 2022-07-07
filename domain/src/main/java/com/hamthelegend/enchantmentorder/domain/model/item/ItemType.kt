package com.hamthelegend.enchantmentorder.domain.model.item

import com.hamthelegend.enchantmentorder.domain.model.enchantment.EnchantmentType
import com.hamthelegend.enchantmentorder.domain.model.enchantment.EnchantmentType.*

enum class ItemType(
    val friendlyName: String,
    val compatibleEnchantmentTypes: Set<EnchantmentType>,
    val defaultEnchantmentTypes: Set<EnchantmentType>,
) {
    EnchantedBook(
        friendlyName = "Enchanted Book",
        compatibleEnchantmentTypes = EnchantmentType.values().toSet(),
        defaultEnchantmentTypes = setOf(),
    ),
    Helmet(
        friendlyName = "Helmet",
        compatibleEnchantmentTypes = setOf(
            AquaAffinity,
            BlastProtection,
            CurseOfBinding,
            CurseOfVanishing,
            FireProtection,
            Mending,
            ProjectileProtection,
            Protection,
            Respiration,
            Thorns,
            Unbreaking,
        ),
        defaultEnchantmentTypes = setOf(
            AquaAffinity,
            Mending,
            Protection,
            Respiration,
            Thorns,
            Unbreaking,
        ),
    ),
    Chestplate(
        friendlyName = "Chestplate",
        compatibleEnchantmentTypes = setOf(
            BlastProtection,
            CurseOfBinding,
            CurseOfVanishing,
            FireProtection,
            Mending,
            ProjectileProtection,
            Protection,
            Thorns,
            Unbreaking,
        ),
        defaultEnchantmentTypes = setOf(
            Mending,
            Protection,
            Thorns,
            Unbreaking,
        ),
    ),
    Leggings(
        friendlyName = "Leggings",
        compatibleEnchantmentTypes = setOf(
            BlastProtection,
            CurseOfBinding,
            CurseOfVanishing,
            FireProtection,
            Mending,
            ProjectileProtection,
            Protection,
            SwiftSneak,
            Thorns,
            Unbreaking,
        ),
        defaultEnchantmentTypes = setOf(
            Mending,
            Protection,
            SwiftSneak,
            Thorns,
            Unbreaking,
        ),
    ),
    Boots(
        friendlyName = "Boots",
        compatibleEnchantmentTypes = setOf(
            BlastProtection,
            CurseOfBinding,
            CurseOfVanishing,
            DepthStrider,
            FeatherFalling,
            FireProtection,
            FrostWalker,
            Mending,
            ProjectileProtection,
            Protection,
            SoulSpeed,
            Thorns,
            Unbreaking,
        ),
        defaultEnchantmentTypes = setOf(
            DepthStrider,
            FeatherFalling,
            Mending,
            Protection,
            SoulSpeed,
            Thorns,
            Unbreaking,
        ),
    ),
    Elytra(
        friendlyName = "Elytra",
        compatibleEnchantmentTypes = setOf(
            CurseOfBinding,
            CurseOfVanishing,
            Mending,
            Unbreaking,
        ),
        defaultEnchantmentTypes = setOf(
            Mending,
            Unbreaking,
        ),
    ),
    Head(
        friendlyName = "Head",
        compatibleEnchantmentTypes = setOf(
            CurseOfBinding,
            CurseOfVanishing,
        ),
        defaultEnchantmentTypes = setOf(
            CurseOfBinding,
        ),
    ),
    Sword(
        friendlyName = "Sword",
        compatibleEnchantmentTypes = setOf(
            BaneOfArthropods,
            CurseOfVanishing,
            FireAspect,
            Knockback,
            Looting,
            Mending,
            Sharpness,
            Smite,
            SweepingEdge,
            Unbreaking,
        ),
        defaultEnchantmentTypes = setOf(
            FireAspect,
            Knockback,
            Looting,
            Mending,
            Sharpness,
            SweepingEdge,
            Unbreaking,
        ),
    ),
    Axe(
        friendlyName = "Axe",
        compatibleEnchantmentTypes = setOf(
            BaneOfArthropods,
            CurseOfVanishing,
            Efficiency,
            Fortune,
            Mending,
            Sharpness,
            SilkTouch,
            Smite,
            Unbreaking,
        ),
        defaultEnchantmentTypes = setOf(
            Efficiency,
            Mending,
            Sharpness,
            SilkTouch,
            Unbreaking,
        ),
    ),
    Pickaxe(
        friendlyName = "Pickaxe",
        compatibleEnchantmentTypes = setOf(
            CurseOfVanishing,
            Efficiency,
            Fortune,
            Mending,
            SilkTouch,
            Unbreaking,
        ),
        defaultEnchantmentTypes = setOf(
            Efficiency,
            Mending,
            SilkTouch,
            Unbreaking,
        ),
    ),
    Shovel(
        friendlyName = "Shovel",
        compatibleEnchantmentTypes = setOf(
            CurseOfVanishing,
            Efficiency,
            Fortune,
            Mending,
            SilkTouch,
            Unbreaking,
        ),
        defaultEnchantmentTypes = setOf(
            Efficiency,
            Mending,
            SilkTouch,
            Unbreaking,
        ),
    ),
    Hoe(
        friendlyName = "Hoe",
        compatibleEnchantmentTypes = setOf(
            CurseOfVanishing,
            Efficiency,
            Fortune,
            Mending,
            SilkTouch,
            Unbreaking,
        ),
        defaultEnchantmentTypes = setOf(
            Efficiency,
            Mending,
            SilkTouch,
            Unbreaking,
        ),
    ),
    Bow(
        friendlyName = "Bow",
        compatibleEnchantmentTypes = setOf(
            CurseOfVanishing,
            Flame,
            Infinity,
            Mending,
            Power,
            Punch,
            Unbreaking,
        ),
        defaultEnchantmentTypes = setOf(
            Flame,
            Infinity,
            Power,
            Punch,
            Unbreaking,
        ),
    ),
    FishingRod(
        friendlyName = "Fishing Rod",
        compatibleEnchantmentTypes = setOf(
            CurseOfVanishing,
            LuckOfTheSea,
            Lure,
            Mending,
            Unbreaking,
        ),
        defaultEnchantmentTypes = setOf(
            LuckOfTheSea,
            Lure,
            Mending,
            Unbreaking,
        ),
    ),
    Trident(
        friendlyName = "Trident",
        compatibleEnchantmentTypes = setOf(
            Channeling,
            CurseOfVanishing,
            Impaling,
            Loyalty,
            Mending,
            Riptide,
            Unbreaking,
        ),
        defaultEnchantmentTypes = setOf(
            Channeling,
            Impaling,
            Loyalty,
            Mending,
            Unbreaking,
        ),
    ),
    Crossbow(
        friendlyName = "Crossbow",
        compatibleEnchantmentTypes = setOf(
            CurseOfVanishing,
            Mending,
            Multishot,
            Piercing,
            QuickCharge,
            Unbreaking,
        ),
        defaultEnchantmentTypes = setOf(
            Mending,
            Multishot,
            QuickCharge,
            Unbreaking,
        ),
    ),
    Shears(
        friendlyName = "Shears",
        compatibleEnchantmentTypes = setOf(
            CurseOfVanishing,
            Efficiency,
            Mending,
            Unbreaking,
        ),
        defaultEnchantmentTypes = setOf(
            Efficiency,
            Mending,
            Unbreaking,
        ),
    ),
    FlintAndSteel(
        friendlyName = "Flint and Steel",
        compatibleEnchantmentTypes = setOf(
            CurseOfVanishing,
            Mending,
            Unbreaking,
        ),
        defaultEnchantmentTypes = setOf(
            Mending,
            Unbreaking,
        ),
    ),
    CarrotOnAStick(
        friendlyName = "Carrot on a Stick",
        compatibleEnchantmentTypes = setOf(
            CurseOfVanishing,
            Mending,
            Unbreaking,
        ),
        defaultEnchantmentTypes = setOf(
            Mending,
            Unbreaking,
        ),
    ),
    WarpedFungusOnAStick(
        friendlyName = "Warped Fungus on a Stick",
        compatibleEnchantmentTypes = setOf(
            CurseOfVanishing,
            Mending,
            Unbreaking
        ),
        defaultEnchantmentTypes = setOf(
            Mending,
            Unbreaking,
        ),
    ),
    ;

}

val targetableItemTypes = ItemType.values().toList() - ItemType.EnchantedBook