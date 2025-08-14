package kr.yjkim.book_search.data

import kr.yjkim.book_search.data.network.KakaoService
import kr.yjkim.book_search.data.network.RetrofitClient
import kr.yjkim.book_search.data.schema.BookItem

class BookSearchRepository {

    private val kakaoService: KakaoService = RetrofitClient.kakaoService

    suspend fun searchKeyword(keyword: String): List<BookItem> {
        return try {
            val response = kakaoService.getBookList(keyword)
            if (response.isSuccessful) {
                val result = response.body()!!
                result.bookList
            } else {
                emptyList()
            }
        } catch (e: Exception) {
            emptyList()
        }
    }
}