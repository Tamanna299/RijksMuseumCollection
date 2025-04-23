package com.example.rijksmuseumcollection.feature_search.domain.use_case

import com.example.rijksmuseumcollection.feature_search.domain.model.ArtDetailObject
import com.example.rijksmuseumcollection.feature_search.domain.repository.SearchRepository
import com.example.rijksmuseumcollection.feature_search.domain.util.Resource
import javax.inject.Inject

class GetArtObjectDetail @Inject constructor(
    private val repository: SearchRepository
) {
    suspend operator fun invoke(objectNumber: String): Resource<ArtDetailObject> {
        return repository.fetchArtDetail(objectNumber)
    }
}