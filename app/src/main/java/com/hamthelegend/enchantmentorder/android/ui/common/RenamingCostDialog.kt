package com.hamthelegend.enchantmentorder.android.ui.screens.addinitialenchantments

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.hamthelegend.enchantmentorder.android.R
import com.hamthelegend.enchantmentorder.composables.TextButton
import com.hamthelegend.enchantmentorder.composables.rememberMutableStateOf

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RenamingCostDialog(
    dismiss: () -> Unit,
    confirm: (renamingCost: Int) -> Unit,
) {
    var renamingCost by rememberMutableStateOf(value = "")

    AlertDialog(
        icon = {
            Icon(
                painter = painterResource(id = R.drawable.xp),
                contentDescription = stringResource(R.string.xp),
                modifier = Modifier.size(24.dp),
            )
        },
        title = {
            Text(text = stringResource(R.string.renaming_cost))
        },
        text = {
            TextField(
                value = renamingCost,
                onValueChange = { newValue ->
                    renamingCost = newValue.toIntOrNull()?.toString() ?: ""
                },
                placeholder = {
                    Text(text = "1")
                },
            )
        },
        confirmButton = {
            TextButton(
                onClick = {
                    confirm(renamingCost.toIntOrNull() ?: 1)
                    dismiss()
                },
                text = stringResource(R.string.confirm),
            )
        },
        dismissButton = {
            TextButton(
                onClick = dismiss,
                text = stringResource(R.string.cancel),
            )
        },
        onDismissRequest = dismiss,
    )
}