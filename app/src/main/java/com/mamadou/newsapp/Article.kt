package com.mamadou.newsapp

data class Article(
    val author: String? = null,
    val content: String? = null,
    val description: String,
    val publishedAt: String,
    val source: Source,
    val title: String,
    val url: String? = null,
    val urlToImage: String
)
