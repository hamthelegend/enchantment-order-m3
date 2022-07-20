package com.hamthelegend.enchantmentorder.android.ui.screens.chooseedition

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.twotone.DesktopWindows
import androidx.compose.material.icons.twotone.Devices
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.hamthelegend.enchantmentorder.android.R
import com.hamthelegend.enchantmentorder.android.ui.screen.Screen
import com.hamthelegend.enchantmentorder.android.ui.screens.destinations.ChooseTargetScreenDestination
import com.hamthelegend.enchantmentorder.android.ui.theme.EnchantmentOrderTheme
import com.hamthelegend.enchantmentorder.android.ui.theme.ThemeIcons
import com.hamthelegend.enchantmentorder.composables.ImageTextCard
import com.hamthelegend.enchantmentorder.domain.models.edition.Edition
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun ChooseEditionScreen(navigator: DestinationsNavigator) {
    ChooseEdition(
        navigateUp = navigator::navigateUp,
        chooseTarget = { editionPicked ->
            navigator.navigate(ChooseTargetScreenDestination(editionPicked))
        }
    )
}

@Composable
fun ChooseEdition(
    navigateUp: () -> Unit,
    chooseTarget: (editionPicked: Edition) -> Unit,
) {
    Screen(
        title = stringResource(R.string.choose_edition),
        navigateUp = navigateUp,
    ) {
        Column {
            ImageTextCard(
                imageVector = ThemeIcons.DesktopWindows,
                text = stringResource(R.string.java_edition),
                onClick = { chooseTarget(Edition.Java) },
                modifier = Modifier.fillMaxWidth(),
            )
            ImageTextCard(
                imageVector = ThemeIcons.Devices,
                text = stringResource(R.string.bedrock_edition),
                onClick = { chooseTarget(Edition.Bedrock) },
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

@Preview
@Composable
fun ChooseEditionPreview() {
    EnchantmentOrderTheme {
        ChooseEdition(
            navigateUp = {},
            chooseTarget = {},
        )
    }
}