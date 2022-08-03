package com.hamthelegend.enchantmentorder.android.ui.screens.result

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.twotone.Done
import androidx.compose.material.icons.twotone.DoneAll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hamthelegend.enchantmentorder.android.R
import com.hamthelegend.enchantmentorder.android.ui.screens.TotalXpCost
import com.hamthelegend.enchantmentorder.android.ui.theme.EnchantmentOrderTheme
import com.hamthelegend.enchantmentorder.android.ui.theme.ThemeIcons
import com.hamthelegend.enchantmentorder.composables.VerticalSpacer
import com.hamthelegend.enchantmentorder.domain.extensions.combineWith
import com.hamthelegend.enchantmentorder.domain.extensions.new
import com.hamthelegend.enchantmentorder.domain.models.enchantment.Enchantment
import com.hamthelegend.enchantmentorder.domain.models.enchantment.EnchantmentType
import com.hamthelegend.enchantmentorder.domain.models.item.Item
import com.hamthelegend.enchantmentorder.domain.models.item.ItemType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Product(
    product: Item,
    totalCost: Int,
    compact: Boolean,
    modifier: Modifier = Modifier,
) {
    Card(modifier = modifier.animateContentSize()) {
        if (!compact) {
            NonCompactProductContent(product = product, totalCost = totalCost)
        } else {
            TotalXpCost(
                cost = totalCost,
                modifier = Modifier.padding(12.dp).align(Alignment.CenterHorizontally)
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun NonCompactProductContent(
    product: Item,
    totalCost: Int,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Card(
            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary),
            modifier = Modifier
                .size(24.dp),
            shape = CircleShape,
        ) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize(),
            ) {
                Icon(
                    imageVector = ThemeIcons.DoneAll,
                    contentDescription = stringResource(R.string.product),
                    modifier = Modifier.padding(4.dp),
                )
            }
        }
        VerticalSpacer(height = 16.dp)
        Item(item = product, )
        VerticalSpacer(height = 16.dp)
        TotalXpCost(cost = totalCost)
    }
}

@Preview
@Composable
fun NonCompactProductPreview() {
    EnchantmentOrderTheme {
        Product(
            product = Item(ItemType.Pickaxe, listOf(Enchantment(EnchantmentType.Mending))),
            totalCost = 500,
            compact = false,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}

@Preview
@Composable
fun CompactProductPreview() {
    EnchantmentOrderTheme {
        Product(
            product = Item(ItemType.Pickaxe, listOf(Enchantment(EnchantmentType.Mending))),
            totalCost = 500,
            compact = true,
            modifier = Modifier.fillMaxWidth(),
        )
    }
}