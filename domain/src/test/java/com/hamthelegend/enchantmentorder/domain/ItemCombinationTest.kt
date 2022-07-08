package com.hamthelegend.enchantmentorder.domain

import com.hamthelegend.enchantmentorder.domain.businesslogic.enchantedBook
import com.hamthelegend.enchantmentorder.domain.businesslogic.new
import com.hamthelegend.enchantmentorder.domain.businesslogic.plus
import com.hamthelegend.enchantmentorder.domain.exceptions.IncompatibleItemTypesException
import com.hamthelegend.enchantmentorder.domain.exceptions.NoCompatibleEnchantmentsException
import com.hamthelegend.enchantmentorder.domain.models.enchantment.Enchantment
import com.hamthelegend.enchantmentorder.domain.models.enchantment.EnchantmentType
import com.hamthelegend.enchantmentorder.domain.models.enchantment.EnchantmentType.*
import com.hamthelegend.enchantmentorder.domain.models.item.ItemType
import com.hamthelegend.enchantmentorder.domain.models.item.ItemType.*
import org.junit.Test

class ItemCombinationTest {

    @Test
    fun regularCombination() {
        val combination = new(Pickaxe) +
                enchantedBook(Enchantment(Fortune, 3))
        combination.assert(
            6,
            Pickaxe,
            Enchantment(Fortune, 3)
        )
    }

    @Test
    fun chainedCombination() {

        val combination1 = new(Pickaxe) +
                enchantedBook(Enchantment(Fortune, 3))
        combination1.assert(
            6,
            Pickaxe,
            Enchantment(Fortune, 3),
        )

        val combination2 = combination1.product +
                enchantedBook(Enchantment(Mending))
        combination2.assert(
            3,
            Pickaxe,
            Enchantment(Fortune, 3),
            Enchantment(Mending),
        )

        val combination3 = combination2.product +
                enchantedBook(Enchantment(Unbreaking, 3))
        combination3.assert(
            6,
            Pickaxe,
            Enchantment(Fortune, 3),
            Enchantment(Mending),
            Enchantment(Unbreaking, 3),
        )

        val combination4 = combination3.product +
                enchantedBook(Enchantment(Efficiency, 5))
        combination4.assert(
            12,
            Pickaxe,
            Enchantment(Efficiency, 5),
            Enchantment(Fortune, 3),
            Enchantment(Mending),
            Enchantment(Unbreaking, 3),
        )

    }

    @Test
    fun noCompatibleEnchantments1() {
        assert<NoCompatibleEnchantmentsException> {
            new(Pickaxe) +
                    enchantedBook(Enchantment(Protection, 4))
        }
    }

    @Test
    fun noCompatibleEnchantments2() {
        assert<NoCompatibleEnchantmentsException> {
            new(Pickaxe, Enchantment(Unbreaking, 3)) +
                    enchantedBook(
                        Enchantment(Protection, 4),
                        Enchantment(FrostWalker, 2),
                    )
        }
    }

    @Test
    fun incompatibleItemTypes1() {
        assert<IncompatibleItemTypesException> {
            new(Pickaxe, Enchantment(Mending)) +
                    new(Shovel, Enchantment(Fortune, 3))
        }
    }

    @Test
    fun incompatibleItemTypes2() {
        assert<IncompatibleItemTypesException> {
            enchantedBook(Enchantment(Mending)) +
                    new(Shovel, Enchantment(Fortune, 3))
        }
    }

    @Test
    fun hasEnchantmentsIncompatibleWithItemType1() {
        val combination = new(Pickaxe, Enchantment(Mending)) +
                enchantedBook(
                    Enchantment(Fortune, 3),
                    Enchantment(Protection, 4),
                )
        combination.assert(
            6,
            Pickaxe,
            Enchantment(Fortune, 3),
            Enchantment(Mending),
        )
    }

    @Test
    fun hasEnchantmentsIncompatibleWithItemType2() {
        val combination1 = new(Pickaxe, Enchantment(Mending)) +
                enchantedBook(
                    Enchantment(Fortune, 3),
                    Enchantment(Protection, 4),
                )
        combination1.assert(
            6,
            Pickaxe,
            Enchantment(Mending),
            Enchantment(Fortune, 3),
        )
        val combination2 = combination1.product +
                enchantedBook(
                    Enchantment(Efficiency, 5),
                    Enchantment(SilkTouch)
                )
        combination2.assert(
            7,
            Pickaxe,
            Enchantment(Mending),
            Enchantment(Fortune, 3),
            Enchantment(Efficiency, 5),
        )
    }

    @Test
    fun hasEnchantmentsIncompatibleWithTargetEnchantments1() {
        val combination = new(Pickaxe, Enchantment(Fortune, 3)) +
                enchantedBook(
                    Enchantment(SilkTouch),
                    Enchantment(Unbreaking, 3),
                )
        combination.assert(
            4,
            Pickaxe,
            Enchantment(Fortune, 3),
            Enchantment(Unbreaking, 3),
        )
    }

    @Test
    fun hasEnchantmentsIncompatibleWithTargetEnchantments2() {
        val combination1 = new(Chestplate, Enchantment(Protection, 4)) +
                enchantedBook(
                    Enchantment(Unbreaking, 2),
                    Enchantment(BlastProtection, 3),
                )
        combination1.assert(
            3,
            Chestplate,
            Enchantment(Protection, 4),
            Enchantment(Unbreaking, 2),
        )
    }

    @Test
    fun hasUpgradableDuplicate1() {
        val combination = new(Chestplate, Enchantment(Protection, 2)) +
                new(Chestplate, Enchantment(Protection, 3))
        combination.assert(
            3,
            Chestplate,
            Enchantment(Protection, 3),
        )
    }

    @Test
    fun hasUpgradableDuplicate2() {
        val combination = new(
            Chestplate,
            Enchantment(Protection, 2),
            Enchantment(Unbreaking, 3),
        ) +
                new(
                    Chestplate,
                    Enchantment(Mending),
                    Enchantment(Protection, 3)
                )
        combination.assert(
            7,
            Chestplate,
            Enchantment(Mending),
            Enchantment(Protection, 3),
            Enchantment(Unbreaking, 3),
        )
    }

    @Test
    fun hasUpgradableDuplicate3() {
        val combination = new(Chestplate, Enchantment(Protection, 3)) +
                new(Chestplate, Enchantment(Protection, 3))
        combination.assert(
            4,
            Chestplate,
            Enchantment(Protection, 4),
        )
    }

    @Test
    fun hasUpgradableDuplicate4() {
        val combination = new(Chestplate, Enchantment(Protection, 3)) +
                enchantedBook(Enchantment(Protection, 3))
        combination.assert(
            4,
            Chestplate,
            Enchantment(Protection, 4),
        )
    }

    @Test
    fun hasUpgradableDuplicate5() {
        val combination = new(
            Chestplate,
            Enchantment(Protection, 3),
            Enchantment(Unbreaking, 3),
        ) +
                enchantedBook(
                    Enchantment(Mending),
                    Enchantment(Protection, 3),
                )
        combination.assert(
            6,
            Chestplate,
            Enchantment(Mending),
            Enchantment(Protection, 4),
            Enchantment(Unbreaking, 3),
        )
    }

    @Test
    fun onlyHasNonUpgradableDuplicate1() {
        assert<NoCompatibleEnchantmentsException> {
            new(Chestplate, Enchantment(Protection, 4)) +
                    new(Chestplate, Enchantment(Protection, 4))
        }
    }

    @Test
    fun onlyHasNonUpgradableDuplicate2() {
        assert<NoCompatibleEnchantmentsException> {
            new(
                Chestplate,
                Enchantment(Protection, 4),
                Enchantment(Unbreaking, 3)
            ) +
                    new(
                        Chestplate,
                        Enchantment(Protection, 4),
                    )
        }
    }

}