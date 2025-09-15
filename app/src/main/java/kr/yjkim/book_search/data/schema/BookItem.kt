package kr.yjkim.book_search.data.schema

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import java.io.Serializable

@JsonClass(generateAdapter = true)
data class BookItem(
    @Json(name = "title") val title: String,
    @Json(name = "contents") val contents: String,
    @Json(name = "url") val url: String,
    @Json(name = "isbn") val isbn: String,
    @Json(name = "datetime") val datetime: String,
    @Json(name = "authors") val authors: List<String>,
    @Json(name = "publisher") val publisher: String,
    @Json(name = "translators") val translators: List<String>,
    @Json(name = "price") val price: Int,
    @Json(name = "sale_price") val salePrice: Int,
    @Json(name = "thumbnail") val thumbnail: String,
    @Json(name = "status") val status: String,
): Serializable
