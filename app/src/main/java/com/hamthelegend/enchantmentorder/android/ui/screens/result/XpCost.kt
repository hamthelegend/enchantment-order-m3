package com.hamthelegend.enchantmentorder.android.ui.screens.result

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.hamthelegend.enchantmentorder.android.R

@Composable
fun XpCost(
    cost: Int,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier) {
        Text(
            text = stringResource(id = R.string.cost, cost),
            style = MaterialTheme.typography.labelSmall,
        )
        Image(
            painter = painterResource(id = R.drawable.xp),
            contentDescription = stringResource(id = R.string.xp),
            modifier = Modifier.size(16.dp)
        )
    }
}

@Composable
fun CompactXpCost(
    cost: Int,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier) {
        Text(
            text = cost.toString(),
            style = MaterialTheme.typography.labelSmall,
        )
        Image(
            painter = painterResource(id = R.drawable.xp),
            contentDescription = stringResource(id = R.string.xp),
            modifier = Modifier.size(16.dp)
        )
    }
}