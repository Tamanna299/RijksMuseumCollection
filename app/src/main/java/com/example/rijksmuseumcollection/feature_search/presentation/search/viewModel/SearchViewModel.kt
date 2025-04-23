package com.example.rijksmuseumcollection.feature_search.presentation.search.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rijksmuseumcollection.core.Constants.PAGE_SIZE
import com.example.rijksmuseumcollection.feature_search.domain.use_case.GetSearchResults
import com.example.rijksmuseumcollection.feature_search.domain.util.Resource
import com.example.rijksmuseumcollection.feature_search.presentation.search.state.SearchUiState
import com.example.rijksmuseumcollection.feature_search.presentation.util.SingleJobRunner
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val getSearchResults: GetSearchResults
) : ViewModel() {

    private val _state = MutableStateFlow(SearchUiState())
    val state: StateFlow<SearchUiState> = _state.asStateFlow()

    private val searchJobRunner = SingleJobRunner()
    private var lastQuery: String? = null

    fun onSearchQueryChange(query: String) = _state.update { it.copy(query = query) }

    fun search() {
        val query = _state.value.query.trim()
        if (query.isBlank() || query == lastQuery) return
        lastQuery = query

        searchJobRunner.launchIn(viewModelScope) {
            _state.update {
                it.copy(
                    isLoading = true,
                    hasSearched = true,
                    currentPage = 1,
                    results = emptyList(),
                    totalCount = 0,
                    error = null
                )
            }
            when (val result = getSearchResults(query, 1, PAGE_SIZE)) {
                is Resource.Success -> {
                    result.data?.let {
                        _state.update {
                            it.copy(
                                results = result.data.artObjects,
                                totalCount = result.data.count,
                                isLoading = false,
                                currentPage = 1
                            )
                        }
                    }
                }
                is Resource.Error -> {
                    _state.update { it.copy(isLoading = false, error = result.message) }
                }
                is Resource.Loading -> {
                    _state.update { it.copy(isLoading = true) }
                }
            }
        }
    }

    fun loadMore() {
        val currentState = _state.value
        if (currentState.isLoading || currentState.results.size >= currentState.totalCount) return

        val nextPage = currentState.currentPage + 1
        viewModelScope.launch {
            when (val result = getSearchResults(currentState.query, nextPage, PAGE_SIZE)) {
                is Resource.Success -> {
                    result.data?.let {
                        _state.update {
                            it.copy(
                                results = it.results + (result.data.artObjects),
                                currentPage = nextPage
                            )
                        }
                    }
                }
                is Resource.Error -> {
                    _state.update { it.copy(isLoading = false, error = result.message) }
                }
                is Resource.Loading -> {
                    _state.update { it.copy(isLoading = true) }
                }
            }
        }
    }
}

