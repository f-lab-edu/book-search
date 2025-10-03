package kr.yjkim.book_search.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kr.yjkim.book_search.data.BookSearchRepository
import kr.yjkim.book_search.util.ResultUiState
import javax.inject.Inject

@HiltViewModel
class ListViewModel @Inject constructor(private val repository: BookSearchRepository): ViewModel() {

    val searchResult: StateFlow<ResultUiState> = repository.searchResult

    fun retry(keyword: String) {
        viewModelScope.launch {
            repository.searchKeyword(keyword)
        }
    }
}