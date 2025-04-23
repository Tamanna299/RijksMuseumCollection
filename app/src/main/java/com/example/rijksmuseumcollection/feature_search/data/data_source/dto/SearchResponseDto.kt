package com.example.rijksmuseumcollection.feature_search.data.data_source.dto

data class SearchResponseDto(
    val count: Int,
    val artObjects: List<ArtObjectDto>
)