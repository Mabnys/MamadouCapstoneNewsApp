package com.mamadou.newsapp.models

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Article(
    @field:Json(name = "author") val author: String? = null,
    @field:Json(name = "title") val title: String,
    @field:Json(name = "description") val description: String,
    @field:Json(name = "url") val url: String? = null,
    @field:Json(name = "urlToImage") val urlToImage: String,
    @field:Json(name = "publishedAt") val publishedAt: String,
    @field:Json(name = "content") val content: String? = null,
    @field:Json(name = "source") val source: Source,
) : Parcelable
