package com.hamthelegend.enchantmentorder.android.ui.screens.result

import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hamthelegend.enchantmentorder.android.ui.theme.EnchantmentOrderTheme
import com.hamthelegend.enchantmentorder.composables.VerticalSpacer
import com.hamthelegend.enchantmentorder.domain.extensions.combineWith
import com.hamthelegend.enchantmentorder.domain.extensions.enchantedBook
import com.hamthelegend.enchantmentorder.domain.extensions.new
import com.hamthelegend.enchantmentorder.domain.models.combination.Combination
import com.hamthelegend.enchantmentorder.domain.models.edition.Edition
import com.hamthelegend.enchantmentorder.domain.models.enchantment.Enchantment
import com.hamthelegend.enchantmentorder.domain.models.enchantment.EnchantmentType
import com.hamthelegend.enchantmentorder.domain.models.item.ItemType

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun Combination(
    index: Int,
    combination: Combination,
    compact: Boolean,
    modifier: Modifier = Modifier,
) {
    Card(modifier = modifier.animateContentSize()) {
        if (!compact) {
            NonCompactCombinationContent(index, combination)
        } else {
            CompactCombinationContent(index, combination)
        }
    }
}

@Composable
private fun NonCompactCombinationContent(
    index: Int,
    combination: Combination,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Index(index = index)
        VerticalSpacer(height = 16.dp)
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
        ) {
            Item(
                item = combination.target,
                modifier = Modifier.weight(1f),
            )
            Text(
                text = "+",
                modifier = Modifier.padding(4.dp)
            )
            Item(
                item = combination.sacrifice,
                modifier = Modifier.weight(1f),
            )
        }
        VerticalSpacer(height = 16.dp)
        XpCost(cost = combination.cost)
    }
}

@Composable
private fun CompactCombinationContent(
    index: Int,
    combination: Combination,
    modifier: Modifier = Modifier,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(12.dp),
    ) {
        Index(index = index)
        VerticalSpacer(height = 16.dp)
        CompactItem(
            item = combination.target,
            modifier = Modifier.weight(1f),
        )
        Text(
            text = "+",
            modifier = Modifier.padding(4.dp)
        )
        CompactItem(
            item = combination.sacrifice,
            modifier = Modifier.weight(1f),
        )
        VerticalSpacer(height = 16.dp)
        CompactXpCost(cost = combination.cost)
    }
}

@Preview
@Composable
fun NonCompactCombinationPreview() {
    EnchantmentOrderTheme {
        Combination(
            index = 1,
            combination = new(ItemType.Pickaxe).combineWith(
                enchantedBook(Enchantment(EnchantmentType.Mending)),
                Edition.Java
            ),
            compact = false,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
    }
}

@Preview
@Composable
fun CompactCombinationPreview() {
    EnchantmentOrderTheme {
        Combination(
            index = 1,
            combination = new(ItemType.Pickaxe).combineWith(
                enchantedBook(Enchantment(EnchantmentType.Mending)),
                Edition.Java),
            compact = true,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )
    }
}