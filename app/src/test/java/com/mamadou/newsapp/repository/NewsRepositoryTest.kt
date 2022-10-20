package com.mamadou.newsapp.repository

import com.mamadou.newsapp.database.dao.ArticleDao
import com.mamadou.newsapp.database.dao.ArticleSourceDao
import com.mamadou.newsapp.database.dao.SourceDao
import com.mamadou.newsapp.models.Article
import com.mamadou.newsapp.networking.NetworkStatusChecker
import com.mamadou.newsapp.networking.RemoteApiService
import com.mamadou.newsapp.preferences.PreferencesDataStore
import com.mamadou.newsapp.utils.CustomResult
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

@ExperimentalCoroutinesApi
class NewsRepositoryTest {
    private val mockArticleDao = mockk<ArticleDao>()
    private val mockArticleSourceDao = mockk<ArticleSourceDao>()
    private val mockSourceDao = mockk<SourceDao>()
    private val mockNewApiService = mockk<RemoteApiService>()
    private val mockDataStore =  mockk<PreferencesDataStore>()
    private val mockNetworkStatusChecker = mockk<NetworkStatusChecker>()

    private val sut = NewsRepositoryImpl(
        mockArticleDao,
        mockArticleSourceDao,
        mockSourceDao,
        mockNewApiService,
        mockDataStore,
        mockNetworkStatusChecker
    )

    @Test
    fun getArticlesFromLocalDb() = runTest {
        coEvery {
            mockArticleDao.getArticles()
        } returns emptyList()
        sut.getArticles().onEach { result ->
            assertEquals(CustomResult.Success(emptyList<Article>()), result)
        }
    }

    @Test
    fun getArticlesFromNetwork() = runTest {
        coEvery {
            mockArticleDao.getArticles()
        } returns emptyList()
        sut.getArticles().onEach { result ->
            assertEquals(CustomResult.Success(emptyList<Article>()), result)
        }
    }


    @Test
    fun searchArticlesReturnsData() = runTest {
        coEvery {
            mockArticleDao.searchArticles("")
        } returns emptyList()
        val articles = sut.searchArticles("")

        assertEquals(emptyList<Article>(), articles)
    }

}


