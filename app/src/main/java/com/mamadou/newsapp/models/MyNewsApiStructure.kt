package com.mamadou.newsapp.models

data class MyNewsApiStructure(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int,
)