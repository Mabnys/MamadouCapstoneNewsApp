package com.mamadou.newsapp.database.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.mamadou.newsapp.database.NewsDatabase
import com.mamadou.newsapp.models.Article
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
//            ApplicationProvider.getApplicationContext(),
            NewsDatabase::class.java).build()
        articleDao = newsDatabase.articleDao()
    }

    @After
//    @Throws(IOException::class)
    fun closeDb() {
        newsDatabase.close()
    }

    @Test
    fun getArticlesReturnsEmptyList() = runBlocking {
        val newsArticle = articleDao.getArticles()
        Assert.assertEquals(emptyList<Article>(), newsArticle)
    }

//    @Test
//    fun addArticles() = runBlocking {
//        articleDao.addArticles()
//        Assert.assertEquals(articleDao.getArticles().size, 6)
//    }

    @Test
    fun searchArticles() = runBlocking {
        val searchString = "2022"
        // val article = articleDao.searchArticles(searchString).first()
        Assert.assertEquals(articleDao.searchArticles(searchString),"2022")
//        Assert.assertNotNull(article)
    }

    @Test
    fun deleteArticles() = runBlocking {
        articleDao.clearArticles()
        Assert.assertEquals(articleDao.getArticles().size, 0)
    }






}