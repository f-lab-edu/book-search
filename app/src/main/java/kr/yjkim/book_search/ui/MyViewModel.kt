package kr.yjkim.book_search.ui

import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.launch
import kr.yjkim.book_search.data.BookSearchRepository

class MyViewModel(private val repository: BookSearchRepository): ViewModel() {

    private var _keyword: String = ""
    val keyword: String
        get() = _keyword

    val books = repository.books

    fun searchKeyword(word: String) {
        _keyword = word
        viewModelScope.launch {
            repository.searchKeyword(word)
        }
    }

    companion object {
        fun create(repository: BookSearchRepository): ViewModelProvider.Factory {
            return viewModelFactory {
                initializer {
                    MyViewModel(repository)
                }
            }
        }
    }
}