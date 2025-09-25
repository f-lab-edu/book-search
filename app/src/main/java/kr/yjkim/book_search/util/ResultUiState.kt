package kr.yjkim.book_search.util

import kr.yjkim.book_search.data.schema.BookItem

sealed class ResultUiState {
    object Loading: ResultUiState()
    data class Success(val bookList: List<BookItem>): ResultUiState()
    data class Error(val exception: Throwable): ResultUiState()
}