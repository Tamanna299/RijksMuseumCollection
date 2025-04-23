package com.example.rijksmuseumcollection.feature_search.domain.repository

import com.example.rijksmuseumcollection.feature_search.domain.model.ArtDetailObject
import com.example.rijksmuseumcollection.feature_search.domain.model.SearchResponse
import com.example.rijksmuseumcollection.feature_search.domain.util.Resource

interface SearchRepository {

    suspend fun search(query: String, page: Int, pageSize: Int? = 10): Resource<SearchResponse>
    suspend fun fetchArtDetail( objectNumber: String ): Resource<ArtDetailObject>
}