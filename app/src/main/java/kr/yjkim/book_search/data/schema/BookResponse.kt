package kr.yjkim.book_search.data.schema

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BookResponse(
    val meta: BookMeta,
    @Json(name = "documents") val bookList: List<BookItem>,
)
