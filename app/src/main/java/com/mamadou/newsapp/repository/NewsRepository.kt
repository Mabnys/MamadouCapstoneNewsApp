package com.mamadou.newsapp.repository

import com.mamadou.newsapp.models.Article
import com.mamadou.newsapp.models.Source
import com.mamadou.newsapp.models.relations.ArticleSource
import com.mamadou.newsapp.utils.CustomResult
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    suspend fun getArticles(): Flow<CustomResult<List<Article>>>

    suspend fun addArticles(articles: List<Article>)

    suspend fun clearArticles(article: Article)

    suspend fun searchArticles(search: String): List<Article>

    suspend fun getArticleFromSources(articleTitle: String): ArticleSource

    suspend fun getSources(): Source

    suspend fun addSources(source: Source)

    suspend fun clearSources()


}