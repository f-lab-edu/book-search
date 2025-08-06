package kr.yjkim.book_search.data.network

import kr.yjkim.book_search.data.network.MoshiBuilder.moshi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitClient {

    val kakaoService: KakaoService = Retrofit.Builder()
        .baseUrl("https://dapi.kakao.com/")
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .build()
        .create(KakaoService::class.java)
}