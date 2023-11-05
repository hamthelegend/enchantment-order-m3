package com.hamthelegend.enchantmentorder.domain

import com.hamthelegend.enchantmentorder.domain.extensions.anvilUseCountToCost
import com.hamthelegend.enchantmentorder.domain.extensions.costToAnvilUseCount
import org.junit.Test

class AnvilUseCountTest {

    @Test
    fun anvilUseCount1() {
        val anvilUseCount = 1
        val cost = anvilUseCount.anvilUseCountToCost()
        val calculatedAnvilUseCount = cost.costToAnvilUseCount()
        assert(anvilUseCount == calculatedAnvilUseCount)
    }

    @Test
    fun anvilUseCount2() {
        val anvilUseCount = 2
        val cost = anvilUseCount.anvilUseCountToCost()
        val calculatedAnvilUseCount = cost.costToAnvilUseCount()
        assert(anvilUseCount == calculatedAnvilUseCount)
    }

    @Test
    fun anvilUseCount3() {
        val anvilUseCount = 3
        val cost = anvilUseCount.anvilUseCountToCost()
        val calculatedAnvilUseCount = cost.costToAnvilUseCount()
        assert(anvilUseCount == calculatedAnvilUseCount)
    }

    @Test
    fun anvilUseCount4() {
        val anvilUseCount = 4
        val cost = anvilUseCount.anvilUseCountToCost()
        val calculatedAnvilUseCount = cost.costToAnvilUseCount()
        assert(anvilUseCount == calculatedAnvilUseCount)
    }

    @Test
    fun anvilUseCount5() {
        val anvilUseCount = 5
        val cost = anvilUseCount.anvilUseCountToCost()
        val calculatedAnvilUseCount = cost.costToAnvilUseCount()
        assert(anvilUseCount == calculatedAnvilUseCount)
    }


}