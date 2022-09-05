package com.mamadou.newsapp.models

data class Source(
    val id: String? = null,
    val name: String,
    val article: List<Article>,
    val category: CategoryType,
    val language: LanguageType,
    val country: CountryType,
)
