package kr.yjkim.book_search.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import kr.yjkim.book_search.data.BookSearchRepository

class InfoViewModel(val repository: BookSearchRepository): ViewModel() {

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