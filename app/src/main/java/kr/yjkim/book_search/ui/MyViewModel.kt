package kr.yjkim.book_search.ui

import androidx.lifecycle.*
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kotlinx.coroutines.launch
import kr.yjkim.book_search.data.BookSearchRepository
import kr.yjkim.book_search.data.schema.BookItem

class MyViewModel(private val repository: BookSearchRepository): ViewModel() {

    private var _keyword: String = ""
    val keyword: String
        get() = _keyword

    private val _books: MutableLiveData<List<BookItem>> = MutableLiveData()
    val books: LiveData<List<BookItem>> get() = _books

    fun searchKeyword(word: String) {
        _keyword = word
        viewModelScope.launch {
            _books.value = repository.searchKeyword(word)
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