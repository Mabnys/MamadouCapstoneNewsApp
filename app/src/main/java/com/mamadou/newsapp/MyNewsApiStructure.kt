package com.mamadou.newsapp

data class MyNewsApiStructure(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)