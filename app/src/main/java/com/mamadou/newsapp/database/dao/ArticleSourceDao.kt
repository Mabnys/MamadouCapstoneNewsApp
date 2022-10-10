package com.mamadou.newsapp.database.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.mamadou.newsapp.models.relations.ArticleSource
@Dao
interface ArticleSourceDao {
    // Fetching all the articles that share the same articleTitle
    @Transaction
    @Query("SELECT * FROM articles WHERE title = :articleTitle")
    fun getArticleFromSources(articleTitle: String): ArticleSource

}