package com.example.rijksmuseumcollection.feature_search.data.data_source

import com.example.rijksmuseumcollection.feature_search.data.data_source.dto.DetailResponseDto
import com.example.rijksmuseumcollection.feature_search.data.data_source.dto.SearchResponseDto
import retrofit2.http.*

interface RijksMuseumApi {

    @GET("collection")
    suspend fun getCollection(
        @Query("q") query: String,
        @Query("s") sortBy: String = "relevance",
        @Query("p") page: Int,
        @Query("ps") pageSize: Int = 10,
        @Query("imgonly") imgOnly: Boolean = true
    ): SearchResponseDto

    @GET("collection/{object-number}")
    suspend fun getCollectionDetails(
        @Path("object-number") objectNumber: String,
    ): DetailResponseDto
}


