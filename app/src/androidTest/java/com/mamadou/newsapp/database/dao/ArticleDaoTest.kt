package com.mamadou.newsapp.database.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.mamadou.newsapp.database.NewsDatabase
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ArticleDaoTest {
    private lateinit var newsDatabase: NewsDatabase
    private lateinit var articleDao: ArticleDao

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun initDb() = runBlocking {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        newsDatabase = Room.inMemoryDatabaseBuilder(context,
            NewsDatabase::class.java).build()
        articleDao = newsDatabase.articleDao()
        articleDao.getArticles()
        articleDao.addArticles(ArticleSourceFactory.getArticles())
    }

    @After
    fun closeDb() {
        newsDatabase.close()
    }

    @Test
    fun getArticles() = runBlocking {
        val newsArticle = articleDao.getArticles()
        Assert.assertEquals(newsArticle.size, 5)
    }

    @Test
    fun addArticles() = runBlocking {
        articleDao.addArticles(ArticleSourceFactory.getArticles())
        Assert.assertEquals(articleDao.getArticles().size, 5)
    }

    @Test
    fun searchArticles() = runBlocking {
        val searchString = "Futures ebb as Powell's speech nears - Reuters"
        val article = articleDao.searchArticles(searchString).first()
        Assert.assertNotNull(article)

    }

    @Test
    fun deleteArticles() = runBlocking {
        articleDao.clearArticles()
        Assert.assertEquals(articleDao.getArticles().size, 0)
    }






}