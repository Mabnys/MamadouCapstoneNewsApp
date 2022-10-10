package com.mamadou.newsapp.repository

import com.mamadou.newsapp.database.dao.ArticleDao
import com.mamadou.newsapp.database.dao.ArticleSourceDao
import com.mamadou.newsapp.database.dao.SourceDao
import com.mamadou.newsapp.models.Article
import com.mamadou.newsapp.models.Source
import com.mamadou.newsapp.models.relations.ArticleSource

class NewsRepositoryImpl(
    private val articleDao: ArticleDao,
    private val articleSourceDao: ArticleSourceDao,
    private val sourceDao: SourceDao
) : NewsRepository {

    override suspend fun getArticles(): List<Article>  = articleDao.getArticles()

    override suspend fun addArticles(articles: List<Article>) = articleDao.addArticles(articles)

    override suspend fun clearArticles(article: Article) {
        TODO("Not yet implemented")
    }

    override suspend fun getArticleFromSources(articleTitle: String): ArticleSource {
        TODO("Not yet implemented")
    }

    override suspend fun getSources(): Source {
        TODO("Not yet implemented")
    }

    override suspend fun addSources(source: Source) {
        TODO("Not yet implemented")
    }

    override suspend fun clearSources() {
        TODO("Not yet implemented")
    }


}