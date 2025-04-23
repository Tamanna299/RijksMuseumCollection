package com.example.rijksmuseumcollection.feature_search.domain.use_case

import com.example.rijksmuseumcollection.feature_search.data.repository.FakeSearchRepository
import com.example.rijksmuseumcollection.feature_search.domain.util.Resource
import com.google.common.truth.Truth.assertThat
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.runBlocking

import org.junit.Before
import org.junit.Test

class GetSearchResultsTest {

    private lateinit var getSearchResults: GetSearchResults
    private lateinit var getArtObjectDetail: GetArtObjectDetail
    private lateinit var fakeSearchRepository: FakeSearchRepository

    @Before
    fun setUp() {
        fakeSearchRepository = FakeSearchRepository()
        getSearchResults = GetSearchResults(fakeSearchRepository)
        getArtObjectDetail = GetArtObjectDetail(fakeSearchRepository)
    }

    @Test
    fun `search returns Success with correct results`() = runBlocking {
        val result = getSearchResults.invoke("rembrandt", 1, 10)

        assertTrue(result is Resource.Success)

        val data = (result as Resource.Success).data
        assertEquals(1, data?.artObjects?.size)
        assertEquals("Painting 1", data?.artObjects?.first()?.title)
        assertEquals("Rembrandt", data?.artObjects?.first()?.principalOrFirstMaker)
    }

    @Test
    fun `searching query dummyArtist should return results `(): Unit = runBlocking {
        val getSearchResults = getSearchResults.invoke("rembrandt", 1, 10)
        assertThat(getSearchResults.data?.artObjects?.isNotEmpty())
    }

    @Test
    fun `searching query other than dummyArtist returns Resource Error `(): Unit = runBlocking {
        val getSearchResults = getSearchResults.invoke("bhjhb", 1, 10)
        assertTrue(getSearchResults is Resource.Error)
    }

    @Test
    fun `fetchArtDetail returns Success with expected data`() = runBlocking {
        val result = getArtObjectDetail.invoke("123")

        assertTrue(result is Resource.Success)

        val data = (result as Resource.Success).data
        assertEquals("Long Sample Title", data?.longTitle)
        assertEquals("John Doe", data?.principalOrFirstMaker)
        assertEquals("This is a sample description.", data?.description)
    }
}
