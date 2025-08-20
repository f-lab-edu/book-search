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

    private val _bookList: MutableLiveData<List<BookItem>> = MutableLiveData()
    val bookList: LiveData<List<BookItem>> get() = _bookList
    private val _errorState: MutableLiveData<Throwable?> = MutableLiveData()
    val errorState: LiveData<Throwable?> get() = _errorState

    suspend fun searchKeyword(keyword: String) {
        try {
            val bookResponse = kakaoService.getBookList(keyword)
            bookResponse.bookList.let { list ->
                if (list.isNotEmpty()) {
                    _bookList.value = list
                } else {
                    _errorState.postValue(NoDataException())
                }
            }
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            _errorState.postValue(e)
        }
    }
}