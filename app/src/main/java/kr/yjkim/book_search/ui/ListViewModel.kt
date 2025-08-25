package kr.yjkim.book_search.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kr.yjkim.book_search.data.BookSearchRepository
import kr.yjkim.book_search.data.schema.BookItem

class ListViewModel(repository: BookSearchRepository): ViewModel() {

    val searchResult: LiveData<Result<List<BookItem>>> = repository.searchResult

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