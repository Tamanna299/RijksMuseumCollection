package com.example.rijksmuseumcollection.feature_search.domain.model

data class ArtDetailObject(
    val objectNumber: String,
    val longTitle: String,
    val webImage: WebImage,
    val principalOrFirstMaker: String,
    val description: String
)