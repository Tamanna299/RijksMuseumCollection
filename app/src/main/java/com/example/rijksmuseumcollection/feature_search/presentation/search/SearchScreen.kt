package com.example.rijksmuseumcollection.feature_search.presentation.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.rijksmuseumcollection.R
import com.example.rijksmuseumcollection.feature_search.presentation.components.ArtCard
import com.example.rijksmuseumcollection.feature_search.presentation.components.CustomText
import com.example.rijksmuseumcollection.feature_search.presentation.search.viewModel.SearchViewModel
import com.example.rijksmuseumcollection.feature_search.presentation.util.getStatusBarHeight

@Composable
fun SearchScreen(
    viewModel: SearchViewModel = hiltViewModel(),
    onNavigateToDetail: (String) -> Unit
) {
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current

    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .padding(top = getStatusBarHeight())
    ) {
        Text(
            stringResource(R.string.title_search_collection),
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            TextField(
                value = state.query,
                onValueChange = viewModel::onSearchQueryChange,
                modifier = Modifier
                    .weight(1f)
                    .height(56.dp)
                    .clip(RoundedCornerShape(32.dp)),
                placeholder = { Text(stringResource(R.string.search_collection)) },
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.LightGray,
                    unfocusedContainerColor = Color.LightGray,
                    disabledContainerColor = Color.LightGray,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    cursorColor = Color.Black
                )
            )
            Spacer(modifier = Modifier.width(8.dp))
            IconButton(
                onClick = {
                    focusManager.clearFocus(force = true)
                    keyboardController?.hide()
                    viewModel.search()
                }) {
                Icon(Icons.Default.Search, contentDescription = stringResource(R.string.search))
            }
            Spacer(modifier = Modifier.height(32.dp))
        }
        when {
            state.isLoading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            }
            !state.hasSearched -> {
                CustomText(stringResource(R.string.search_collection))
            }
            state.results.isEmpty() -> {
                Spacer(modifier = Modifier.height(32.dp))
                if (state.error != null) {
                    CustomText(state.error!!.asString())
                } else {
                    CustomText(stringResource(R.string.no_results_found))
                }
            }
            else -> {
                Spacer(modifier = Modifier.height(24.dp))
                Text(stringResource(R.string.search_results_found) + state.totalCount)
                Spacer(modifier = Modifier.height(16.dp))
                LazyColumn {
                    itemsIndexed(state.results) { index, art ->
                        ArtCard(art, onNavigateToDetail)
                        if (index == state.results.lastIndex - 5) {
                            viewModel.loadMore()
                        }
                    }
                }
            }
        }
    }
}

