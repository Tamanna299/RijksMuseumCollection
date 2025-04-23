package com.example.rijksmuseumcollection.feature_search.presentation.util

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {
    @Serializable
    data object Search : Screen()

    @Serializable
    data class Detail(
        val objectNumber: String
    ) : Screen()
}