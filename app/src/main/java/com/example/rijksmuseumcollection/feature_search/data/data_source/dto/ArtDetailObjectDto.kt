package com.example.rijksmuseumcollection.feature_search.data.data_source.dto

data class ArtDetailObjectDto(
    val objectNumber: String?,
    val longTitle: String?,
    val webImage: WebImageDto?,
    val principalOrFirstMaker: String?,
    val description: String?
)