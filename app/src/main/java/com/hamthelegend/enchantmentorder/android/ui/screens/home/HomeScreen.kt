package com.hamthelegend.enchantmentorder.android.ui.screens.home

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.twotone.DesktopMac
import androidx.compose.material.icons.twotone.Devices
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hamthelegend.enchantmentorder.android.R
import com.hamthelegend.enchantmentorder.android.ui.screen.Screen
import com.hamthelegend.enchantmentorder.android.ui.screens.InterstitialAdViewModel
import com.hamthelegend.enchantmentorder.android.ui.screens.SubscriptionViewModel
import com.hamthelegend.enchantmentorder.android.ui.screens.destinations.ChooseTargetScreenDestination
import com.hamthelegend.enchantmentorder.android.ui.theme.EnchantmentOrderTheme
import com.hamthelegend.enchantmentorder.android.ui.theme.ThemeIcons
import com.hamthelegend.enchantmentorder.composables.TextButton
import com.hamthelegend.enchantmentorder.composables.VerticalSpacer
import com.hamthelegend.enchantmentorder.domain.models.edition.Edition
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RootNavGraph(start = true)
@Destination
@Composable
fun HomeScreen(
    navigator: DestinationsNavigator,
    subscriptionViewModel: SubscriptionViewModel,
    interstitialAdViewModel: InterstitialAdViewModel,
) {
    Home(
        premium = subscriptionViewModel.premium ?: false,
        purchasePremium = subscriptionViewModel::purchase,
        loadInterstitialAd = interstitialAdViewModel::loadInterstitialAd,
        navigateToChooseTargetScreen = { edition ->
            navigator.navigate(ChooseTargetScreenDestination(edition))
        },
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(
    premium: Boolean,
    purchasePremium: () -> Unit,
    loadInterstitialAd: () -> Unit,
    navigateToChooseTargetScreen: (Edition) -> Unit,
) {
    Screen(showAd = !premium) {
        Column(
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize(),
        ) {
            Icon(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = stringResource(R.string.logo),
                modifier = Modifier
                    .padding(horizontal = 32.dp)
                    .size(80.dp),
            )
            VerticalSpacer(height = 16.dp)
            Text(
                text = stringResource(id = R.string.choose_edition),
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 32.dp),
            )
            VerticalSpacer(height = 16.dp)
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.background,
                ),
                shape = RectangleShape,
                onClick = { navigateToChooseTargetScreen(Edition.Java) },
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(
                            horizontal = 32.dp,
                            vertical = 16.dp,
                        )
                        .fillMaxWidth(),
                ) {
                    Icon(
                        imageVector = ThemeIcons.DesktopMac,
                        contentDescription = stringResource(id = R.string.java_edition),
                        modifier = Modifier.size(24.dp),
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = stringResource(id = R.string.java_edition),
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
            }
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.background,
                ),
                shape = RectangleShape,
                onClick = { navigateToChooseTargetScreen(Edition.Bedrock) },
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .padding(
                            horizontal = 32.dp,
                            vertical = 16.dp,
                        )
                        .fillMaxWidth(),
                ) {
                    Icon(
                        imageVector = ThemeIcons.Devices,
                        contentDescription = stringResource(id = R.string.bedrock_edition),
                        modifier = Modifier.size(24.dp),
                    )
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = stringResource(id = R.string.bedrock_edition),
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
            }
            if (!premium) {
                VerticalSpacer(height = 16.dp)
                TextButton(
                    onClick = purchasePremium,
                    text = stringResource(R.string.remove_ads),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp),
                )
            }
        }
    }
    LaunchedEffect(Unit) {
        if (!premium) loadInterstitialAd()
    }
}

@Preview
@Composable
fun HomeScreenPreview() {
    EnchantmentOrderTheme {
        Home(
            premium = false,
            purchasePremium = {},
            navigateToChooseTargetScreen = {},
            loadInterstitialAd = {},
        )
    }
}