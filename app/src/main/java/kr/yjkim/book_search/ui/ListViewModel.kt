package kr.yjkim.book_search.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kr.yjkim.book_search.data.BookSearchRepository
import kr.yjkim.book_search.util.ResultUiState

class ListViewModel(private val repository: BookSearchRepository): ViewModel() {

    val searchResult: StateFlow<ResultUiState> = repository.searchResult

    fun retry(keyword: String) {
        viewModelScope.launch {
            repository.searchKeyword(keyword)
        }
    }

    companion object {
        fun create(repo: BookSearchRepository): ViewModelProvider.Factory {
            return viewModelFactory {
                initializer {
                    ListViewModel(repo)
                }
            }
        }
    }
}