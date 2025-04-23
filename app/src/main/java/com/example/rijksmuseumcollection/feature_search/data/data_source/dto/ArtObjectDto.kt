package com.example.rijksmuseumcollection.feature_search.data.data_source.dto

data class ArtObjectDto(
    val objectNumber: String?,
    val title: String?,
    val webImage: WebImageDto?,
    val principalOrFirstMaker: String?,
    val objectType: String?
)