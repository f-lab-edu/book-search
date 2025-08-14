package kr.yjkim.book_search.data

import kotlinx.coroutines.CancellationException
import kr.yjkim.book_search.data.network.KakaoService
import kr.yjkim.book_search.data.network.RetrofitClient
import kr.yjkim.book_search.data.schema.BookItem

class BookSearchRepository {

    private val kakaoService: KakaoService = RetrofitClient.kakaoService

    suspend fun searchKeyword(keyword: String): List<BookItem> {
        return try {
            val bookResponse = kakaoService.getBookList(keyword)
            bookResponse.bookList
        } catch (e: CancellationException) {
            throw e
        } catch (_: Exception) {
            emptyList()
        }
    }
}