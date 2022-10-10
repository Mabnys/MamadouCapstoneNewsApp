package com.mamadou.newsapp.database.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.mamadou.newsapp.models.Article
import com.mamadou.newsapp.models.Source

@Dao
interface SourceDao {
    @Query("SELECT * FROM sources")
    suspend fun getSources(): Source

    @Insert(onConflict = REPLACE)
    suspend fun addSources(source: Source)

    @Query("DELETE FROM sources")
    suspend fun clearSources()

//    @Query("SELECT * FROM articles WHERE articleId = :articlesId")
//    suspend fun getArticleById(articlesId: String): Article
}