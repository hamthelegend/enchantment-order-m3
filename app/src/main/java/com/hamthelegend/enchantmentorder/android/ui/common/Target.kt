package com.hamthelegend.enchantmentorder.android.ui.common

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.twotone.Checklist
import androidx.compose.material.icons.twotone.RestartAlt
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hamthelegend.enchantmentorder.android.R
import com.hamthelegend.enchantmentorder.android.ui.res.imageResId
import com.hamthelegend.enchantmentorder.android.ui.theme.EnchantmentOrderTheme
import com.hamthelegend.enchantmentorder.android.ui.theme.ThemeIcons
import com.hamthelegend.enchantmentorder.composables.*
import com.hamthelegend.enchantmentorder.domain.extensions.displayString
import com.hamthelegend.enchantmentorder.domain.models.enchantment.Enchantment
import com.hamthelegend.enchantmentorder.domain.models.enchantment.EnchantmentType.*
import com.hamthelegend.enchantmentorder.domain.models.item.Item
import com.hamthelegend.enchantmentorder.domain.models.item.ItemType.*

@OptIn(ExperimentalMaterial3Api::class, ExperimentalAnimationApi::class)
@Composable
fun Target(
    target: Item,
    hasSelection: Boolean,
    selectDefaults: () -> Unit,
    resetSelection: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Card(modifier = modifier) {
        Column(modifier = Modifier.padding(12.dp)) {
            Row (verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(id = target.type.imageResId),
                    contentDescription = target.type.friendlyName,
                    modifier = Modifier.size(24.dp),
                )
                HorizontalSpacer(width = 16.dp)
                Column {
                    Text(
                        text = target.type.friendlyName,
                        style = MaterialTheme.typography.titleMedium,
                    )
                    Text(
                        text = target.enchantments.displayString ?:
                        stringResource(R.string.unenchanted),
                        style = MaterialTheme.typography.labelMedium,
                    )
                }
            }
            VerticalSpacer(height = 8.dp)
            OutlinedButton(
                onClick = { if (hasSelection) resetSelection() else selectDefaults() },
                modifier = Modifier.fillMaxWidth(),
            ) {
                AnimatedContent(targetState = hasSelection) {
                    if (hasSelection) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = ThemeIcons.RestartAlt,
                                contentDescription = stringResource(id = R.string.reset_selection),
                            )
                            HorizontalSpacer(width = 8.dp)
                            Text(text = stringResource(id = R.string.reset_selection))
                        }
                    } else {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = ThemeIcons.Checklist,
                                contentDescription = stringResource(id = R.string.select_defaults),
                            )
                            HorizontalSpacer(width = 8.dp)
                            Text(text = stringResource(id = R.string.select_defaults))
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun TargetPreview() {
    EnchantmentOrderTheme {
        var hasSelection by rememberMutableStateOf(value = false)

        Target(
            target = Item(Pickaxe, listOf(Enchantment(Mending))),
            hasSelection = hasSelection,
            selectDefaults = { hasSelection = true },
            resetSelection = { hasSelection = false },
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
        )
    }
}