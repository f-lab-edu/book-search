package kr.yjkim.book_search.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.map
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kr.yjkim.book_search.R
import kr.yjkim.book_search.data.BookSearchRepository
import kr.yjkim.book_search.data.schema.BookItem
import kr.yjkim.book_search.util.NoDataException
import retrofit2.HttpException
import java.io.IOException

class ListViewModel(repository: BookSearchRepository): ViewModel() {

    val bookList: LiveData<List<BookItem>> = repository.bookList
    val errorMessage: LiveData<Int?> = repository.errorState.map { e ->
        when (e) {
            null -> null
            is IOException -> R.string.error_network
            is HttpException -> R.string.error_server
            is NoDataException -> R.string.error_no_data
            else -> R.string.error_unknown
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