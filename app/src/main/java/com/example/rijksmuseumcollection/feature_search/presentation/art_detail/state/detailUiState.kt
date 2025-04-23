package com.example.rijksmuseumcollection.feature_search.presentation.art_detail.state

import com.example.rijksmuseumcollection.feature_search.domain.model.ArtDetailObject
import com.example.rijksmuseumcollection.feature_search.domain.util.UiText

data class DetailUiState(
    val artDetailObject: ArtDetailObject? = null,
    val isLoading: Boolean = false,
    val error: UiText? = null
)