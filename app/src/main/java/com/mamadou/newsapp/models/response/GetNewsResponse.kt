package com.mamadou.newsapp.models.response

import com.mamadou.newsapp.models.Article
import com.squareup.moshi.Json

data class GetNewsResponse(@field:Json(name = "articles") val articles: List<Article> = mutableListOf())
