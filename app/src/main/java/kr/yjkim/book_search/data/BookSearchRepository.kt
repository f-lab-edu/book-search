package kr.yjkim.book_search.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CancellationException
import kr.yjkim.book_search.data.network.KakaoService
import kr.yjkim.book_search.data.network.RetrofitClient
import kr.yjkim.book_search.data.schema.BookItem
import kr.yjkim.book_search.util.NoDataException

object BookSearchRepository {

    private val kakaoService: KakaoService = RetrofitClient.kakaoService

    private val _searchResult: MutableLiveData<Result<List<BookItem>>> = MutableLiveData()
    val searchResult: LiveData<Result<List<BookItem>>> get() = _searchResult

    suspend fun searchKeyword(keyword: String) {
        try {
            val bookResponse = kakaoService.getBookList(keyword)
            _searchResult.value = bookResponse.bookList.let { list ->
                if (list.isNotEmpty()) {
                    Result.success(list)
                } else {
                    Result.failure(NoDataException())
                }
            }
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            _searchResult.postValue(Result.failure(e))
        }
    }
}