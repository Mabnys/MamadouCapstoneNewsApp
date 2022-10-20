package com.mamadou.newsapp.viewmodels

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.mamadou.newsapp.models.Article
import com.mamadou.newsapp.repository.NewsRepository
import com.mamadou.newsapp.utils.CustomResult
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.mockkStatic
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class NewsListViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val newsRepository = mockk<NewsRepository>()

    private lateinit var sut: NewsListViewModel

    @Before
    fun setup() {
        mockkStatic(Log::class)
        this.sut = NewsListViewModel(newsRepository)
    }

    @Test
    fun fetchArticlesToGetTheData() = runTest {
        coEvery {
            newsRepository.getArticles()
        } returns flow { CustomResult.Success(emptyList<Article>()) }
        sut.fetchArticles()

        coVerify(exactly = 5) { newsRepository.getArticles() }
    }

    @Test
    fun initArticlesToGetTheData() = runTest {
        coEvery {
            newsRepository.getArticles()
        } returns flow { CustomResult.Success(emptyList<Article>()) }

        coVerify(exactly = 1) { newsRepository.getArticles() }
    }

}