package com.hamthelegend.enchantmentorder.domain.models.enchantment

import com.hamthelegend.enchantmentorder.domain.models.edition.Edition
import com.hamthelegend.enchantmentorder.domain.models.item.ItemType

enum class EnchantmentType(
    val friendlyName: String,
    val abbreviatedName: String,
    val maxLevel: Int,
    val javaItemMultiplier: Int,
    val javaBookMultiplier: Int,
    val bedrockItemMultiplier: Int = javaItemMultiplier,
    val bedrockBookMultiplier: Int = javaBookMultiplier,
    val inJava: Boolean = true,
    val inBedrock: Boolean = false,
) {

    AquaAffinity(
        friendlyName = "Aqua Affinity",
        abbreviatedName = "AA",
        maxLevel = 1,
        javaItemMultiplier = 4,
        javaBookMultiplier = 2,
    ),
    BaneOfArthropods(
        friendlyName = "Bane of Arthropods",
        abbreviatedName = "BoA",
        maxLevel = 5,
        javaItemMultiplier = 2,
        javaBookMultiplier = 1,
    ),
    BlastProtection(
        friendlyName = "Blast Protection",
        abbreviatedName = "BP",
        maxLevel = 4,
        javaItemMultiplier = 4,
        javaBookMultiplier = 2,
    ),
    Channeling(
        friendlyName = "Channeling",
        abbreviatedName = "Ch",
        maxLevel = 1,
        javaItemMultiplier = 8,
        javaBookMultiplier = 4,
    ),
    CurseOfBinding(
        friendlyName = "Curse of Binding",
        abbreviatedName = "CoB",
        maxLevel = 1,
        javaItemMultiplier = 8,
        javaBookMultiplier = 4,
    ),
    CurseOfVanishing(
        friendlyName = "Curse of Vanishing",
        abbreviatedName = "CoV",
        maxLevel = 1,
        javaItemMultiplier = 8,
        javaBookMultiplier = 4,
    ),
    DepthStrider(
        friendlyName = "Depth Strider",
        abbreviatedName = "DS",
        maxLevel = 3,
        javaItemMultiplier = 4,
        javaBookMultiplier = 2,
    ),
    Efficiency(
        friendlyName = "Efficiency",
        abbreviatedName = "Ef",
        maxLevel = 5,
        javaItemMultiplier = 1,
        javaBookMultiplier = 1,
    ),
    FeatherFalling(
        friendlyName = "Feather Falling",
        abbreviatedName = "FF",
        maxLevel = 4,
        javaItemMultiplier = 2,
        javaBookMultiplier = 1,
    ),
    FireAspect(
        friendlyName = "Fire Aspect",
        abbreviatedName = "FA",
        maxLevel = 2,
        javaItemMultiplier = 2,
        javaBookMultiplier = 2,
    ),
    FireProtection(
        friendlyName = "Fire Protection",
        abbreviatedName = "FP",
        maxLevel = 4,
        javaItemMultiplier = 2,
        javaBookMultiplier = 1,
    ),
    Flame(
        friendlyName = "Flame",
        abbreviatedName = "Fl",
        maxLevel = 1,
        javaItemMultiplier = 4,
        javaBookMultiplier = 2,
    ),
    Fortune(
        friendlyName = "Fortune",
        abbreviatedName = "Fo",
        maxLevel = 3,
        javaItemMultiplier = 4,
        javaBookMultiplier = 2,
    ),
    FrostWalker(
        friendlyName = "Frost Walker",
        abbreviatedName = "FW",
        maxLevel = 2,
        javaItemMultiplier = 4,
        javaBookMultiplier = 2,
    ),
    Impaling(
        friendlyName = "Impaling",
        abbreviatedName = "Im",
        maxLevel = 5,
        javaItemMultiplier = 4,
        javaBookMultiplier = 2,
        bedrockBookMultiplier = 2,
        bedrockItemMultiplier = 1,
    ),
    Infinity(
        friendlyName = "Infinity",
        abbreviatedName = "In",
        maxLevel = 1,
        javaItemMultiplier = 8,
        javaBookMultiplier = 4,
    ),
    Knockback(
        friendlyName = "Knockback",
        abbreviatedName = "Kn",
        maxLevel = 2,
        javaItemMultiplier = 2,
        javaBookMultiplier = 1,
    ),
    Looting(
        friendlyName = "Looting",
        abbreviatedName = "Loo",
        maxLevel = 3,
        javaItemMultiplier = 4,
        javaBookMultiplier = 2,
    ),
    Loyalty(
        friendlyName = "Loyalty",
        abbreviatedName = "Loy",
        maxLevel = 3,
        javaItemMultiplier = 1,
        javaBookMultiplier = 1,
    ),
    LuckOfTheSea(
        friendlyName = "Luck of the Sea",
        abbreviatedName = "LotS",
        maxLevel = 3,
        javaItemMultiplier = 4,
        javaBookMultiplier = 2,
    ),
    Lure(
        friendlyName = "Lure",
        abbreviatedName = "Lu",
        maxLevel = 3,
        javaItemMultiplier = 4,
        javaBookMultiplier = 2,
    ),
    Mending(
        friendlyName = "Mending",
        abbreviatedName = "Me",
        maxLevel = 1,
        javaItemMultiplier = 4,
        javaBookMultiplier = 2,
    ),
    Multishot(
        friendlyName = "Multishot",
        abbreviatedName = "Mu",
        maxLevel = 1,
        javaItemMultiplier = 4,
        javaBookMultiplier = 2,
    ),
    Piercing(
        friendlyName = "Piercing",
        abbreviatedName = "Pi",
        maxLevel = 4,
        javaItemMultiplier = 1,
        javaBookMultiplier = 1,
    ),
    Power(
        friendlyName = "Power",
        abbreviatedName = "Po",
        maxLevel = 5,
        javaItemMultiplier = 1,
        javaBookMultiplier = 1,
    ),
    ProjectileProtection(
        friendlyName = "Projectile Protection",
        abbreviatedName = "PP",
        maxLevel = 4,
        javaItemMultiplier = 2,
        javaBookMultiplier = 1,
    ),
    Protection(
        friendlyName = "Protection",
        abbreviatedName = "Pr",
        maxLevel = 4,
        javaItemMultiplier = 1,
        javaBookMultiplier = 1,
    ),
    Punch(
        friendlyName = "Punch",
        abbreviatedName = "Pu",
        maxLevel = 2,
        javaItemMultiplier = 4,
        javaBookMultiplier = 2,
    ),
    QuickCharge(
        friendlyName = "Quick Charge",
        abbreviatedName = "QC",
        maxLevel = 3,
        javaItemMultiplier = 2,
        javaBookMultiplier = 1,
    ),
    Respiration(
        friendlyName = "Respiration",
        abbreviatedName = "Re",
        maxLevel = 3,
        javaItemMultiplier = 4,
        javaBookMultiplier = 2,
    ),
    Riptide(
        friendlyName = "Riptide",
        abbreviatedName = "Ri",
        maxLevel = 3,
        javaItemMultiplier = 4,
        javaBookMultiplier = 2,
    ),
    Sharpness(
        friendlyName = "Sharpness",
        abbreviatedName = "Sh",
        maxLevel = 5,
        javaItemMultiplier = 1,
        javaBookMultiplier = 1,
    ),
    SilkTouch(
        friendlyName = "Silk Touch",
        abbreviatedName = "ST",
        maxLevel = 1,
        javaItemMultiplier = 8,
        javaBookMultiplier = 4,
    ),
    Smite(
        friendlyName = "Smite",
        abbreviatedName = "Sm",
        maxLevel = 5,
        javaItemMultiplier = 2,
        javaBookMultiplier = 1,
    ),
    SoulSpeed(
        friendlyName = "Soul Speed",
        abbreviatedName = "SSp",
        maxLevel = 3,
        javaItemMultiplier = 8,
        javaBookMultiplier = 4,
    ),
    SweepingEdge(
        friendlyName = "Sweeping Edge",
        abbreviatedName = "SE",
        maxLevel = 3,
        javaItemMultiplier = 4,
        javaBookMultiplier = 2,
        inBedrock = false,
    ),
    SwiftSneak(
        friendlyName = "Swift Sneak",
        abbreviatedName = "SSn",
        maxLevel = 3,
        javaItemMultiplier = 8,
        javaBookMultiplier = 4,
    ),
    Thorns(
        friendlyName = "Thorns",
        abbreviatedName = "Th",
        maxLevel = 3,
        javaItemMultiplier = 8,
        javaBookMultiplier = 4,
    ),
    Unbreaking(
        friendlyName = "Unbreaking",
        abbreviatedName = "Un",
        maxLevel = 3,
        javaItemMultiplier = 2,
        javaBookMultiplier = 1,
    ),
    ;

    val incompatibleEnchantmentTypes: List<EnchantmentType>
        get() = when (this) {
            AquaAffinity -> listOf()
            BaneOfArthropods -> listOf(Sharpness, Smite)
            BlastProtection -> listOf(FireProtection, ProjectileProtection, Protection)
            Channeling -> listOf(Riptide)
            CurseOfBinding -> listOf()
            CurseOfVanishing -> listOf()
            DepthStrider -> listOf(FrostWalker)
            Efficiency -> listOf()
            FeatherFalling -> listOf()
            FireAspect -> listOf()
            FireProtection -> listOf(BlastProtection, ProjectileProtection, Protection)
            Flame -> listOf()
            Fortune -> listOf(SilkTouch)
            FrostWalker -> listOf(DepthStrider)
            Impaling -> listOf()
            Infinity -> listOf(Mending)
            Knockback -> listOf()
            Looting -> listOf()
            Loyalty -> listOf(Riptide)
            LuckOfTheSea -> listOf()
            Lure -> listOf()
            Mending -> listOf(Infinity)
            Multishot -> listOf(Piercing)
            Piercing -> listOf(Multishot)
            Power -> listOf()
            ProjectileProtection -> listOf(BlastProtection, FireProtection, Protection)
            Protection -> listOf(BlastProtection, FireProtection, ProjectileProtection)
            Punch -> listOf()
            QuickCharge -> listOf()
            Respiration -> listOf()
            Riptide -> listOf(Channeling, Loyalty)
            Sharpness -> listOf(BaneOfArthropods, Smite)
            SilkTouch -> listOf(Fortune)
            Smite -> listOf(BaneOfArthropods, Sharpness)
            SoulSpeed -> listOf()
            SweepingEdge -> listOf()
            SwiftSneak -> listOf()
            Thorns -> listOf()
            Unbreaking -> listOf()
        }

    fun multiplierFor(itemType: ItemType, edition: Edition) =
        if (itemType == ItemType.EnchantedBook) {
            when (edition) {
                Edition.Java -> javaBookMultiplier
                Edition.Bedrock -> bedrockBookMultiplier
            }
        } else {
            when (edition) {
                Edition.Java -> javaItemMultiplier
                Edition.Bedrock -> bedrockItemMultiplier
            }
        }

}