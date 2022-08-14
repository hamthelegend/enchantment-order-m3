package com.hamthelegend.enchantmentorder.android.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hamthelegend.enchantmentorder.android.ui.common.BannerAd
import com.hamthelegend.enchantmentorder.android.ui.theme.EnchantmentOrderTheme
import com.hamthelegend.enchantmentorder.composables.rememberDerivedStateOf
import com.hamthelegend.enchantmentorder.composables.rememberMutableStateOf
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screen(
    title: String,
    modifier: Modifier = Modifier,
    showAd: Boolean = false,
    navigateUp: (() -> Unit)? = null,
    searchQuery: String? = null,
    onSearchQueryChange: (newQuery: String) -> Unit = {},
    otherActions: @Composable RowScope.() -> Unit = {},
    scrolled: Boolean = false,
    floatingActionButton: @Composable (Modifier) -> Unit = {},
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    content: @Composable () -> Unit,
) {
    Column {
        Scaffold(
            modifier = modifier.weight(1f),
            topBar = {
                TopBar(
                    title = title,
                    navigateUp = navigateUp,
                    searchQuery = searchQuery,
                    onSearchQueryChange = onSearchQueryChange,
                    otherActions = otherActions,
                    scrolled = scrolled,
                )
            },
            snackbarHost = {
                SnackbarHost(hostState = snackbarHostState)
            },
            floatingActionButton = {
                floatingActionButton(
                    if (showAd) Modifier else Modifier.navigationBarsPadding()
                )
            },
        ) { paddingValues ->
            Surface(
                modifier = Modifier.padding(paddingValues),
                content = content,
            )
        }
        AnimatedVisibility(visible = showAd) {
            Surface(
                color = MaterialTheme.colorScheme.tertiaryContainer,
                modifier = Modifier.animateContentSize(),
            ) {
                BannerAd(
                    modifier = Modifier
                        .padding(8.dp)
                        .navigationBarsPadding(),
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Screen(
    modifier: Modifier = Modifier,
    showAd: Boolean = false,
    floatingActionButton: @Composable (Modifier) -> Unit = {},
    content: @Composable () -> Unit,
) {
    Column {
        Scaffold(
            modifier = modifier
                .weight(1f)
                .systemBarsPadding(),
            floatingActionButton = {
                floatingActionButton(
                    if (showAd) Modifier else Modifier.navigationBarsPadding()
                )
            },
        ) { paddingValues ->
            Surface(
                modifier = Modifier.padding(paddingValues),
                content = content,
            )
        }
        AnimatedVisibility(visible = showAd) {
            Surface(
                color = MaterialTheme.colorScheme.tertiaryContainer,
                modifier = Modifier.animateContentSize(),
            ) {
                BannerAd(
                    modifier = Modifier
                        .padding(8.dp)
                        .navigationBarsPadding(),
                )
            }
        }
    }
}

@Preview
@Composable
fun ScreenWithTopBarPreview() {
    EnchantmentOrderTheme {

        var searchQuery by rememberMutableStateOf(value = "")

        val lazyListState = rememberLazyListState()
        val scrolled by rememberDerivedStateOf {
            lazyListState.firstVisibleItemIndex != 0 ||
                    lazyListState.firstVisibleItemScrollOffset != 0
        }
        val scrollScope = rememberCoroutineScope()

        Screen(
            title = "Enchantment Order",
            navigateUp = {
                scrollScope.launch {
                    lazyListState.animateScrollToItem(0, 0)
                }
            },
            searchQuery = searchQuery,
            onSearchQueryChange = { searchQuery = it },
            scrolled = scrolled,
            showAd = true,
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                state = lazyListState,
            ) {
                items((1..100).toList()) { item ->
                    Text(
                        text = item.toString(),
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun ScreenWithoutAppBarPreview() {
    EnchantmentOrderTheme {

        Screen(showAd = true) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                items((1..100).toList()) { item ->
                    Text(
                        text = item.toString(),
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }

}