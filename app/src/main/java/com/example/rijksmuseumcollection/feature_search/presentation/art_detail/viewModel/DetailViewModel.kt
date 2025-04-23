package com.example.rijksmuseumcollection.feature_search.presentation.art_detail.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rijksmuseumcollection.feature_search.domain.use_case.GetArtObjectDetail
import com.example.rijksmuseumcollection.feature_search.domain.util.Resource
import com.example.rijksmuseumcollection.feature_search.presentation.art_detail.state.DetailUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getArtObjectDetail: GetArtObjectDetail
) : ViewModel() {

    private val _state = MutableStateFlow(DetailUiState())
    val state: StateFlow<DetailUiState> = _state

    fun loadArtDetail(objectNumber: String) {
        if (objectNumber.isEmpty() ) return
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)

            when (val result = getArtObjectDetail(objectNumber)) {
                is Resource.Success -> {
                    if (result.data != null){
                        _state.update {
                            it.copy(
                                artDetailObject = result.data,
                                isLoading = false
                            )
                        }
                    }
                }
                is Resource.Error -> {
                    _state.update { it.copy(isLoading = false, error = result.message) }
                }
                is Resource.Loading -> {
                    _state.update {
                        it.copy(
                            isLoading = true
                        )
                    }
                }
            }
        }
    }
}
