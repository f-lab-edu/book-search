package kr.yjkim.book_search.data.network

import kr.yjkim.book_search.BuildConfig.REST_API_KEY
import kr.yjkim.book_search.data.schema.BookResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface KakaoService {

    @Headers("Authorization: KakaoAK $REST_API_KEY")
    @GET("v3/search/book")
    suspend fun getBookList(
        @Query("query") keyword: String,
    ): Response<BookResponse>
}