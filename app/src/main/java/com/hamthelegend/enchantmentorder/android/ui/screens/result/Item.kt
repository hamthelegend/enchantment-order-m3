package com.hamthelegend.enchantmentorder.android.ui.screens.result

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.hamthelegend.enchantmentorder.android.R
import com.hamthelegend.enchantmentorder.android.ui.res.imageResId
import com.hamthelegend.enchantmentorder.composables.VerticalSpacer
import com.hamthelegend.enchantmentorder.domain.extensions.displayString
import com.hamthelegend.enchantmentorder.domain.models.item.Item

@Composable
fun Item(
    item: Item,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        Icon(
            painter = painterResource(id = item.type.imageResId),
            contentDescription = item.type.friendlyName,
            modifier = Modifier.size(24.dp)
        )
        VerticalSpacer(height = 8.dp)
        Text(
            text = item.type.friendlyName,
            style = MaterialTheme.typography.titleSmall,
            maxLines = 1,
            textAlign = TextAlign.Center,
        )
        for (enchantment in item.enchantments) {
            Text(
                text = enchantment.toString(),
                style = MaterialTheme.typography.bodySmall,
                maxLines = 1,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
fun CompactItem(
    item: Item,
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier,
    ) {
        Icon(
            painter = painterResource(id = item.type.imageResId),
            contentDescription = item.type.friendlyName,
            modifier = Modifier.size(24.dp)
        )
        VerticalSpacer(height = 8.dp)
        Text(
            text = item.enchantments.displayString ?: stringResource(id = R.string.unenchanted),
            style = MaterialTheme.typography.bodySmall,
            maxLines = 1,
            textAlign = TextAlign.Center,
        )
    }
}