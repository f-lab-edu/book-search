package kr.yjkim.book_search.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kr.yjkim.book_search.data.BookSearchRepository
import kr.yjkim.book_search.data.schema.BookItem

class InfoViewModel(val repository: BookSearchRepository): ViewModel() {

    private val _bookItem: MutableLiveData<BookItem> = MutableLiveData()
    val bookItem: LiveData<BookItem> get() = _bookItem

    fun getBookItem(itemId: Int) {
        val result = requireNotNull(repository.searchResult.value)
        _bookItem.value = result.getOrThrow()[itemId]
    }

    companion object {
        fun create(repo: BookSearchRepository): ViewModelProvider.Factory {
            return viewModelFactory {
                initializer {
                    InfoViewModel(repo)
                }
            }
        }
    }
}