package kr.yjkim.book_search.data

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kr.yjkim.book_search.data.network.KakaoService
import kr.yjkim.book_search.data.network.RetrofitClient
import kr.yjkim.book_search.util.ResultUiState

object BookSearchRepository {

    private val kakaoService: KakaoService = RetrofitClient.kakaoService

    private val _searchResult: MutableStateFlow<ResultUiState> = MutableStateFlow(ResultUiState.Loading)
    val searchResult: StateFlow<ResultUiState> get() = _searchResult

    suspend fun searchKeyword(keyword: String) {
        try {
            val bookResponse = kakaoService.getBookList(keyword)
            _searchResult.value = ResultUiState.Success(bookResponse.bookList)
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            _searchResult.value = ResultUiState.Error(e)
        }
    }
}