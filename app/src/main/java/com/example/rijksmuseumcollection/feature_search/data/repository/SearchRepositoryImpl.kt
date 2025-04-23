package com.example.rijksmuseumcollection.feature_search.data.repository

import com.example.rijksmuseumcollection.R
import com.example.rijksmuseumcollection.feature_search.data.data_source.RijksMuseumApi
import com.example.rijksmuseumcollection.feature_search.data.mapper.SearchMapper
import com.example.rijksmuseumcollection.feature_search.domain.model.ArtDetailObject
import com.example.rijksmuseumcollection.feature_search.domain.model.SearchResponse
import com.example.rijksmuseumcollection.feature_search.domain.repository.SearchRepository
import com.example.rijksmuseumcollection.feature_search.domain.util.Resource
import com.example.rijksmuseumcollection.feature_search.domain.util.UiText
import java.io.IOException
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val api: RijksMuseumApi
) : SearchRepository {

    private val searchMapper: SearchMapper = SearchMapper()

    override suspend fun search(
        query: String,
        page: Int,
        pageSize: Int?
    ): Resource<SearchResponse> {
        val searchResult: SearchResponse

        try {
            val response = api.getCollection(query = query, page = page)
            searchResult = searchMapper.mapSearchResult(response)
        } catch (e: IOException) {
            return Resource.Error(UiText.StringResource(R.string.network_error))
        } catch (e: Exception) {
            return Resource.Error(UiText.StringResource(R.string.error))
        }
        return Resource.Success(searchResult)
    }

    override suspend fun fetchArtDetail(objectNumber: String): Resource<ArtDetailObject> {
        val artDetailObject: ArtDetailObject

        try {
            val response = api.getCollectionDetails(objectNumber)
            artDetailObject = searchMapper.mapArtObjectDetail(response.artObject)
        } catch (e: IOException) {
            return Resource.Error(UiText.StringResource(R.string.network_error))
        } catch (e: Exception) {
            return Resource.Error(UiText.StringResource(R.string.error))
        }
        return Resource.Success(artDetailObject)
    }
}