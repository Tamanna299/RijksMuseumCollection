package com.example.rijksmuseumcollection.feature_search.presentation.util

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.statusBars
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun getStatusBarHeight(): Dp {
    val statusBarPadding = WindowInsets.statusBars.asPaddingValues()
        .calculateTopPadding()
    return if (statusBarPadding > 0.dp) statusBarPadding else 24.dp
}