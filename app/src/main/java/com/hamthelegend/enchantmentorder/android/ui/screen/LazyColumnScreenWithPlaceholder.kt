package com.hamthelegend.enchantmentorder.android.ui.screen

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.hamthelegend.enchantmentorder.composables.FullScreenLazyColumn
import com.hamthelegend.enchantmentorder.composables.rememberDerivedStateOf
import kotlinx.serialization.json.JsonNull.content

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun LazyColumnScreenWithPlaceholder(
    title: String,
    placeholder: @Composable (modifier: Modifier) -> Unit,
    usePlaceholder: Boolean,
    modifier: Modifier = Modifier,
    navigateUp: (() -> Unit)? = null,
    searchQuery: String? = null,
    onSearchQueryChange: (newQuery: String) -> Unit = {},
    otherActions: @Composable RowScope.() -> Unit = {},
    floatingActionButton: @Composable () -> Unit = {},
    lazyColumnState: LazyListState = rememberLazyListState(),
    snackbarHostState: SnackbarHostState = remember { SnackbarHostState() },
    content: LazyListScope.() -> Unit,
) {
    val scrolled by rememberDerivedStateOf {
        lazyColumnState.firstVisibleItemIndex != 0 ||
                lazyColumnState.firstVisibleItemScrollOffset != 0
    }

    Screen(
        title = title,
        modifier = modifier,
        navigateUp = navigateUp,
        searchQuery = searchQuery,
        onSearchQueryChange = onSearchQueryChange,
        otherActions = otherActions,
        floatingActionButton = floatingActionButton,
        snackbarHostState = snackbarHostState,
        scrolled = scrolled,
    ) {
        AnimatedContent(targetState = usePlaceholder) { _usePlaceholder ->
            if (_usePlaceholder) {
                Column(modifier = Modifier.fillMaxSize()) {
                    placeholder(modifier.weight(1f))
                    Spacer(modifier = Modifier.navigationBarsPadding())
                }
            } else {
                FullScreenLazyColumn(state = lazyColumnState) {
                    content()
                }
            }
        }
    }
}