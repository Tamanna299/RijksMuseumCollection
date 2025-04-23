package com.example.rijksmuseumcollection.feature_search.data.mapper

import com.example.rijksmuseumcollection.feature_search.domain.model.ArtDetailObject
import com.example.rijksmuseumcollection.feature_search.domain.model.ArtObject
import com.example.rijksmuseumcollection.feature_search.domain.model.SearchResponse
import com.example.rijksmuseumcollection.feature_search.domain.model.WebImage
import com.example.rijksmuseumcollection.feature_search.data.data_source.dto.ArtDetailObjectDto
import com.example.rijksmuseumcollection.feature_search.data.data_source.dto.ArtObjectDto
import com.example.rijksmuseumcollection.feature_search.data.data_source.dto.SearchResponseDto
import com.example.rijksmuseumcollection.feature_search.data.data_source.dto.WebImageDto

class SearchMapper {

    fun mapSearchResult(dto: SearchResponseDto) : SearchResponse {
        return SearchResponse(
            count = dto.count,
            artObjects = mapSearchItems(dto.artObjects)
        )
    }

    private fun mapSearchItems(dto: List<ArtObjectDto>?) : List<ArtObject> {
        return dto?.map { dto ->
            with(dto){
                ArtObject(
                    objectNumber = objectNumber!!,
                    title = title ?: "Untitled",
                    webImage = mapWebImage(webImage),
                    principalOrFirstMaker = principalOrFirstMaker ?: "Unknown Artist",
                )
            }
        } ?: emptyList()
    }

    private fun mapWebImage(dto: WebImageDto?) : WebImage {
        return WebImage(
            url =  dto?.url ?: ""
        )
    }

    fun mapArtObjectDetail(dto: ArtDetailObjectDto): ArtDetailObject {
        return with(dto){
            ArtDetailObject(
                objectNumber = objectNumber!!,
                longTitle = longTitle!!,
                webImage = mapWebImage(webImage),
                principalOrFirstMaker = principalOrFirstMaker!!,
                description = description!!,
            )
        }
    }
}