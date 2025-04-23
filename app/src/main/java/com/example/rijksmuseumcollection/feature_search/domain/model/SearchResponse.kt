package com.example.rijksmuseumcollection.feature_search.domain.model

data class SearchResponse(
    val count: Int,
    val artObjects: List<ArtObject>
)