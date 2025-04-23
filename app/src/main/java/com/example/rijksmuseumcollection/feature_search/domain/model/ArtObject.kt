package com.example.rijksmuseumcollection.feature_search.domain.model

data class ArtObject(
    val objectNumber: String,
    val title: String,
    val webImage: WebImage,
    val principalOrFirstMaker: String,
)