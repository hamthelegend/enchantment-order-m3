package com.hamthelegend.enchantmentorder.android.ui.common

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.twotone.Info
import androidx.compose.material3.*
import androidx.compose.material3.CardDefaults.cardColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hamthelegend.enchantmentorder.android.R
import com.hamthelegend.enchantmentorder.android.ui.theme.EnchantmentOrderTheme
import com.hamthelegend.enchantmentorder.android.ui.theme.ThemeIcons
import com.hamthelegend.enchantmentorder.composables.HorizontalSpacer

@Composable
fun InfoCard(
    text: String,
    modifier: Modifier = Modifier,
) {
    Card(
        colors = cardColors(containerColor = MaterialTheme.colorScheme.secondaryContainer),
        modifier = modifier,
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(12.dp),
        ) {
            Icon(imageVector = ThemeIcons.Info, contentDescription = stringResource(R.string.info))
            HorizontalSpacer(width = 16.dp)
            Text(
                text = text,
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}

@Preview
@Composable
fun InfoCardPreview() {
    EnchantmentOrderTheme {
        InfoCard(
            text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec libero augue, vehicula at elit eu, aliquam eleifend ipsum. Aenean vehicula sapien vitae ligula posuere, nec.",
            modifier = Modifier.fillMaxWidth().padding(4.dp)
        )
    }
}