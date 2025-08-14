package kr.yjkim.book_search.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CancellationException
import kr.yjkim.book_search.data.network.KakaoService
import kr.yjkim.book_search.data.network.RetrofitClient
import kr.yjkim.book_search.data.schema.BookItem

object BookSearchRepository {

    private val kakaoService: KakaoService = RetrofitClient.kakaoService

    private val _bookList: MutableLiveData<List<BookItem>> = MutableLiveData()
    val bookList: LiveData<List<BookItem>> get() = _bookList

    suspend fun searchKeyword(keyword: String) {
        _bookList.value = try {
            val bookResponse = kakaoService.getBookList(keyword)
            bookResponse.bookList
        } catch (e: CancellationException) {
            throw e
        } catch (_: Exception) {
            emptyList()
        }
    }
}