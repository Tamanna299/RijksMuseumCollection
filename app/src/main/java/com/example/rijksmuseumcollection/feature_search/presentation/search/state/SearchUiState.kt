package com.example.rijksmuseumcollection.feature_search.presentation.search.state

import com.example.rijksmuseumcollection.feature_search.domain.model.ArtObject
import com.example.rijksmuseumcollection.feature_search.domain.util.UiText

data class SearchUiState(
    val query: String = "",
    val results: List<ArtObject> = emptyList(),
    val totalCount: Int = 0,
    val isLoading: Boolean = false,
    val currentPage: Int = 1,
    val hasSearched: Boolean = false,
    val error: UiText? = null
)