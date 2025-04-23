package com.example.rijksmuseumcollection.feature_search.domain.use_case

import com.example.rijksmuseumcollection.feature_search.domain.model.SearchResponse
import com.example.rijksmuseumcollection.feature_search.domain.repository.SearchRepository
import com.example.rijksmuseumcollection.feature_search.domain.util.Resource
import javax.inject.Inject

class GetSearchResults @Inject constructor(
    private val repository: SearchRepository
) {
    suspend operator fun invoke(query: String, page: Int, pageSize: Int?): Resource<SearchResponse> {
        return repository.search(query, page, pageSize)
    }
}