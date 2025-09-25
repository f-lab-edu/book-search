package kr.yjkim.book_search.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kr.yjkim.book_search.data.BookSearchRepository

class HomeViewModel(private val repository: BookSearchRepository): ViewModel() {

    private val _keyword: MutableStateFlow<String> = MutableStateFlow("")
    val keyword: StateFlow<String> = _keyword.asStateFlow()

    fun searchKeyword(keyword: String) {
        viewModelScope.launch {
            repository.searchKeyword(keyword)
        }
        _keyword.value = keyword
    }

    companion object {
        fun create(repo: BookSearchRepository): ViewModelProvider.Factory {
            return viewModelFactory {
                initializer {
                    HomeViewModel(repo)
                }
            }
        }
    }
}