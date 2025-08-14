package kr.yjkim.book_search.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.launch
import kr.yjkim.book_search.data.BookSearchRepository

class HomeViewModel(private val repository: BookSearchRepository): ViewModel() {

    fun searchKeyword(keyword: String) {
        viewModelScope.launch {
            repository.searchKeyword(keyword)
        }
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