package com.mamadou.newsapp.repository

import com.mamadou.newsapp.models.Article
import com.mamadou.newsapp.models.Source
import com.mamadou.newsapp.models.relations.ArticleSource

interface NewsRepository {
    suspend fun getArticles(): List<Article>

    suspend fun addArticles(articles: List<Article>)

    suspend fun clearArticles(article: Article)

    suspend fun getArticleFromSources(articleTitle: String): ArticleSource

    suspend fun getSources(): Source

    suspend fun addSources(source: Source)

    suspend fun clearSources()




}