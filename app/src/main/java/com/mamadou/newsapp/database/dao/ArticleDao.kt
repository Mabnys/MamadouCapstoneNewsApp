package com.mamadou.newsapp.database.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.mamadou.newsapp.models.Article
import com.mamadou.newsapp.models.relations.ArticleSource

@Dao
interface ArticleDao {
    @Query("SELECT * FROM articles")
    suspend fun getArticles(): List<Article>

    @Insert(onConflict = REPLACE)
    suspend fun addArticles(articles: List<Article>)

    @Query("DELETE FROM articles")
    suspend fun clearArticles()

}