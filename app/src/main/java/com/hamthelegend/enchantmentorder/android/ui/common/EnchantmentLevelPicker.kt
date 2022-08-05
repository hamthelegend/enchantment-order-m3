package com.hamthelegend.enchantmentorder.android.ui.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hamthelegend.enchantmentorder.android.ui.res.imageResId
import com.hamthelegend.enchantmentorder.android.ui.theme.EnchantmentOrderTheme
import com.hamthelegend.enchantmentorder.composables.Button
import com.hamthelegend.enchantmentorder.composables.HorizontalSpacer
import com.hamthelegend.enchantmentorder.composables.VerticalSpacer
import com.hamthelegend.enchantmentorder.composables.rememberMutableStateOf
import com.hamthelegend.enchantmentorder.domain.models.enchantment.Enchantment
import com.hamthelegend.enchantmentorder.domain.models.enchantment.EnchantmentType
import com.hamthelegend.enchantmentorder.domain.models.item.ItemType
import com.hamthelegend.enchantmentorder.extensions.toRomanNumerals

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EnchantmentLevelPicker(
    enchantmentType: EnchantmentType,
    level: Int?,
    select: (Enchantment) -> Unit,
    deselect: (Enchantment) -> Unit,
    modifier: Modifier = Modifier,
    topActive: Boolean = false,
    bottomActive: Boolean = false,
) {
    val active = level != null
    var pickingLevel by rememberMutableStateOf(value = false)

    val topCornerRadius by animateDpAsState(
        targetValue = when {
            active && topActive -> 0.dp
            active -> 16.dp
            else -> 0.dp
        }
    )
    val bottomCornerRadius by animateDpAsState(
        targetValue = when {
            active && bottomActive -> 0.dp
            active -> 16.dp
            else -> 0.dp
        }
    )
    val containerColor by animateColorAsState(
        targetValue = when {
            active -> MaterialTheme.colorScheme.primaryContainer
            else -> MaterialTheme.colorScheme.background
        }
    )
    val contentColor by animateColorAsState(
        targetValue = when {
            active -> MaterialTheme.colorScheme.onPrimaryContainer
            else -> MaterialTheme.colorScheme.onBackground
        }
    )

    Card(
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = containerColor,
            contentColor = contentColor,
        ),
        shape = MaterialTheme.shapes.medium.copy(
            topStart = CornerSize(topCornerRadius),
            topEnd = CornerSize(topCornerRadius),
            bottomStart = CornerSize(bottomCornerRadius),
            bottomEnd = CornerSize(bottomCornerRadius),
        ),
        onClick = {
            if (level == null) {
                if (enchantmentType.maxLevel == 1) {
                    select(Enchantment(enchantmentType))
                } else {
                    pickingLevel = !pickingLevel
                }
            } else {
                deselect(Enchantment(enchantmentType, level))
            }
        }
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            val name = when (level) {
                null -> enchantmentType.friendlyName
                else -> Enchantment(enchantmentType, level).toString()
            }
            Icon(
                painter = painterResource(id = ItemType.EnchantedBook.imageResId),
                contentDescription = name,
                modifier = Modifier.size(24.dp),
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = name,
                style = MaterialTheme.typography.bodyMedium,
            )
        }
        AnimatedVisibility(visible = pickingLevel) {
            Row(modifier = Modifier
                .padding(start = 16.dp, top = 0.dp, end = 16.dp, bottom = 16.dp)
            ) {
                for (pickableLevel in 1..enchantmentType.maxLevel) {
                    if (pickableLevel != 1) {
                        HorizontalSpacer(width = 8.dp)
                    }
                    Button(
                        onClick = {
                            select(Enchantment(enchantmentType, pickableLevel))
                            pickingLevel = false
                        },
                        text = pickableLevel.toRomanNumerals(),
                        modifier = Modifier.weight(1f),
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun EnchantmentLevelPickerPreview() {
    EnchantmentOrderTheme {
        var level: Int? by rememberMutableStateOf(value = null)

        EnchantmentLevelPicker(
            enchantmentType = EnchantmentType.Mending,
            level = level,
            select = { enchantment ->
                level = enchantment.level
            },
            deselect = {
                level = null
            },
            modifier = Modifier.fillMaxWidth(),
        )
    }
}