package kr.yjkim.book_search.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kr.yjkim.book_search.data.network.KakaoService
import kr.yjkim.book_search.data.network.RetrofitClient
import kr.yjkim.book_search.data.schema.BookItem

class BookSearchRepository {

    private val kakaoService: KakaoService = RetrofitClient.kakaoService

    private val _books: MutableLiveData<List<BookItem>> = MutableLiveData()
    val books: LiveData<List<BookItem>> get() = _books

    suspend fun searchKeyword(keyword: String) {
        try {
            val response = kakaoService.getBookList(keyword)
            if (response.isSuccessful) {
                val result = response.body()!!
                _books.value = result.bookList
            } else {

            }
        } catch (e: Exception) {

        }
    }
}