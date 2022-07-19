package com.hamthelegend.enchantmentorder.android.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hamthelegend.enchantmentorder.android.R
import com.hamthelegend.enchantmentorder.android.ui.theme.EnchantmentOrderTheme
import com.hamthelegend.enchantmentorder.composables.Button
import com.hamthelegend.enchantmentorder.composables.Spacer
import com.hamthelegend.enchantmentorder.composables.TextButton
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph

@RootNavGraph(start = true)
@Destination
@Composable
fun HomeScreenDestination() {
    HomeScreen()
}

@Composable
fun HomeScreen() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = stringResource(R.string.logo),
            modifier = Modifier.size(80.dp),
        )
        Spacer(height = 16.dp)
        Text(
            text = stringResource(id = R.string.app_name),
            style = MaterialTheme.typography.titleLarge,
        )
        Spacer(height = 16.dp)
        Text(
            text = stringResource(R.string.app_description),
            style = MaterialTheme.typography.bodyLarge,
            textAlign = TextAlign.Center,
        )
        Spacer(height = 16.dp)
        Button(
            onClick = { /*TODO*/ },
            text = stringResource(R.string.start_enchanting),
            modifier = Modifier.fillMaxWidth(),
        )
        TextButton(onClick = { /*TODO*/ }, text = stringResource(R.string.saved_enchantments))
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    EnchantmentOrderTheme {
        HomeScreen()
    }
}