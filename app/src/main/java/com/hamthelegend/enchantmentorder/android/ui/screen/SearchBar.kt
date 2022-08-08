package com.hamthelegend.enchantmentorder.android.ui.screen

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.twotone.ArrowBack
import androidx.compose.material.icons.twotone.Clear
import androidx.compose.material.icons.twotone.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.res.stringResource
import com.hamthelegend.enchantmentorder.android.R
import com.hamthelegend.enchantmentorder.android.ui.theme.ThemeIcons
import com.hamthelegend.enchantmentorder.composables.IconButton

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBar(
    query: String,
    onQueryChange: (newQuery: String) -> Unit,
    onStopSearching: () -> Unit,
    modifier: Modifier = Modifier,
    shouldBeInFocus: Boolean = true,
) {
    val focusRequester = remember { FocusRequester() }

    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = modifier
            .fillMaxWidth()
            .focusRequester(focusRequester),
        placeholder = {
            Text(
                text = stringResource(id = R.string.search),
                maxLines = 1,
            )
        },
        leadingIcon = {
            Icon(
                imageVector = ThemeIcons.Search,
                contentDescription = stringResource(id = R.string.search),
            )
        },
        trailingIcon = {
            IconButton(
                imageVector = ThemeIcons.Clear,
                contentDescription = stringResource(id = R.string.clear_search),
                onClick = {
                    onStopSearching()
                    onQueryChange("")
                },
            )
        },
        singleLine = true,
    )

    LaunchedEffect(shouldBeInFocus) {
        if (shouldBeInFocus) focusRequester.requestFocus()
    }
}