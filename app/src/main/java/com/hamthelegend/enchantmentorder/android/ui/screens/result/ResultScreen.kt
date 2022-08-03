package com.hamthelegend.enchantmentorder.android.ui.screens.result

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.twotone.UnfoldLess
import androidx.compose.material.icons.twotone.UnfoldMore
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.hamthelegend.enchantmentorder.android.R
import com.hamthelegend.enchantmentorder.android.ui.screen.LazyColumnScreen
import com.hamthelegend.enchantmentorder.android.ui.screen.LazyColumnScreenWithPlaceholder
import com.hamthelegend.enchantmentorder.android.ui.theme.ThemeIcons
import com.hamthelegend.enchantmentorder.composables.IconButton
import com.hamthelegend.enchantmentorder.composables.VerticalSpacer
import com.hamthelegend.enchantmentorder.domain.models.combination.CombinationOrder
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator.navigateUp

@Destination(navArgsDelegate = ResultNavArgs::class)
@Composable
fun ResultScreen(
    navigator: DestinationsNavigator,
    viewModel: ResultViewModel = hiltViewModel(),
) {
    Result(
        navigateUp = navigator::navigateUp,
        combinationOrder = viewModel.combinationOrder,
        compact = viewModel.compact,
        toggleCompact = viewModel::toggleCompact
    )
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun Result(
    navigateUp: () -> Unit,
    combinationOrder: CombinationOrder?,
    compact: Boolean,
    toggleCompact: () -> Unit,
) {
    LazyColumnScreenWithPlaceholder(
        title = stringResource(R.string.result),
        navigateUp = navigateUp,
        placeholder = { modifier ->
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier.fillMaxWidth(),
            ) {
                CircularProgressIndicator()
                VerticalSpacer(height = 16.dp)
                Text(
                    text = stringResource(R.string.getting_the_best_order),
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        },
        otherActions = {
            AnimatedContent(targetState = compact) { _compact ->
                IconButton(
                    onClick = toggleCompact,
                    imageVector = if (_compact) ThemeIcons.UnfoldMore else ThemeIcons.UnfoldLess,
                    contentDescription = stringResource(
                        if (_compact) R.string.comfortable_view
                        else R.string.compact_view
                    ),
                )
            }
        },
        usePlaceholder = combinationOrder == null,
    ) {
        item {
            VerticalSpacer(height = 4.dp)
        }
        itemsIndexed(
            items = combinationOrder!!.combinations,
            key = { _, combination -> combination.key }
        ) { index, combination ->
            Combination(
                index = index + 1,
                combination = combination,
                compact = compact,
                modifier = Modifier.padding(horizontal = 4.dp)
            )
            VerticalSpacer(height = 4.dp)
        }
        item {
            Product(
                product = combinationOrder.finalProduct,
                totalCost = combinationOrder.totalCost,
                compact = compact,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 4.dp),
            )
            VerticalSpacer(height = 4.dp)
        }
    }
}