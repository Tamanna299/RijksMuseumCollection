package com.example.rijksmuseumcollection.feature_search.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle

@Composable
fun CustomText(
    message: String,
    modifier: Modifier? = Modifier,
    style: TextStyle? = null
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = message,
            modifier = modifier ?: Modifier.align(Alignment.CenterHorizontally),
            style = style ?: MaterialTheme.typography.bodyMedium
        )
    }
}
