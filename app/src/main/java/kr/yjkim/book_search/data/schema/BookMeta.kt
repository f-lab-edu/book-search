package kr.yjkim.book_search.data.schema

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class BookMeta(
    @Json(name = "is_end") val isEnd: Boolean,
    @Json(name = "pageable_count") val pageableCount: Int,
    @Json(name = "total_count") val totalCount: Int,
)
