package com.mamadou.newsapp.repository

import android.util.Log
import com.mamadou.newsapp.database.dao.ArticleDao
import com.mamadou.newsapp.database.dao.ArticleSourceDao
import com.mamadou.newsapp.database.dao.SourceDao
import com.mamadou.newsapp.models.Article
import com.mamadou.newsapp.models.Source
import com.mamadou.newsapp.models.relations.ArticleSource
import com.mamadou.newsapp.networking.NetworkStatusChecker
import com.mamadou.newsapp.networking.RemoteApiService
import com.mamadou.newsapp.preferences.PreferencesDataStore
import com.mamadou.newsapp.utils.CustomResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

const val TOKEN_KEY = "926a9e3d32f545be911ca1e71f9aff72"
class NewsRepositoryImpl(
    private val articleDao: ArticleDao,
    private val articleSourceDao: ArticleSourceDao,
    private val sourceDao: SourceDao,
    private val newApiService: RemoteApiService,
    private val dataStore: PreferencesDataStore,
    private val networkStatusChecker: NetworkStatusChecker

    ) : NewsRepository {

    override suspend fun getArticles(): Flow<CustomResult<List<Article>>> {
        return flow {
            val newsFromLocalDb = articleDao.getArticles()

            emit(CustomResult.Success(newsFromLocalDb))

            Log.i(TAG, "newFromLocalDb size = ${newsFromLocalDb.size}")

            val isDownloadOverWifiOnly = dataStore.isDownloadOverWifiOnly().first()
            if (!isDownloadOverWifiOnly ||  networkStatusChecker.hasWifiConnection()) {
                try {
                    val newsFromNetwork = newApiService
                        .getArticles(TOKEN_KEY)
                        .articles
                    Log.d("RefreshTest","Data fetch from Network")
                    emit(CustomResult.Success(newsFromNetwork))
                    if (newsFromNetwork.isNotEmpty()) {
                        articleDao.clearArticles()
                        sourceDao.clearSources()
                        articleDao.addArticles(newsFromNetwork)
                    }

                } catch (error: Exception) {
                    Log.e(TAG, error.toString())
                }
            }
        }
    }

    override suspend fun addArticles(articles: List<Article>) = articleDao.addArticles(articles)

    override suspend fun clearArticles(article: Article) = articleDao.clearArticles()

    override suspend fun searchArticles(search: String): List<Article> = articleDao.searchArticles(search)

    override suspend fun getArticleFromSources(articleTitle: String): ArticleSource =
        articleSourceDao.getArticleFromSources(articleTitle)

    override suspend fun getSources(): Source = sourceDao.getSources()

    override suspend fun addSources(source: Source) = sourceDao.addSources(source)

    override suspend fun clearSources() = sourceDao.clearSources()

    companion object {
        private const val TAG = "NewsRepositoryImpl"
    }
}


