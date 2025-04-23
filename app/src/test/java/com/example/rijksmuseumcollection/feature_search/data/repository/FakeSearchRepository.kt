package com.example.rijksmuseumcollection.feature_search.data.repository

import com.example.rijksmuseumcollection.R
import com.example.rijksmuseumcollection.feature_search.data.data_source.dto.ArtObjectDto
import com.example.rijksmuseumcollection.feature_search.domain.model.ArtDetailObject
import com.example.rijksmuseumcollection.feature_search.domain.model.ArtObject
import com.example.rijksmuseumcollection.feature_search.domain.model.SearchResponse
import com.example.rijksmuseumcollection.feature_search.domain.model.WebImage
import com.example.rijksmuseumcollection.feature_search.domain.repository.SearchRepository
import com.example.rijksmuseumcollection.feature_search.domain.util.Resource
import com.example.rijksmuseumcollection.feature_search.domain.util.UiText

class FakeSearchRepository : SearchRepository {

    override suspend fun search(
        query: String,
        page: Int,
        pageSize: Int?
    ): Resource<SearchResponse> {

        if(query == "rembrandt") {
            val response =  SearchResponse(
                count = 1,
                artObjects = listOf(
                    ArtObject(
                        objectNumber = "hR-hy-678",
                        title = "Painting 1",
                        webImage = WebImage("https://example.com/art.jpg"),
                        principalOrFirstMaker = "Rembrandt",
                    )
                )
            )
            return Resource.Success(response)
        } else {
            return  Resource.Error(UiText.StringResource(R.string.error))
        }
    }

    override suspend fun fetchArtDetail(objectNumber: String): Resource<ArtDetailObject> {
        val artObject = ArtDetailObject(
            objectNumber = "123",
            longTitle = "Long Sample Title",
            description = "This is a sample description.",
            principalOrFirstMaker = "John Doe",
            webImage = WebImage("https://example.com/image.jpg"),
        )

        return Resource.Success(artObject)
    }
}