package com.mamadou.newsapp.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.mamadou.newsapp.database.converters.SourceConverter
import com.mamadou.newsapp.database.dao.ArticleDao
import com.mamadou.newsapp.database.dao.ArticleSourceDao
import com.mamadou.newsapp.database.dao.SourceDao
import com.mamadou.newsapp.models.Article
import com.mamadou.newsapp.models.Source

const val DATABASE_VERSION = 1
@Database(
    entities = [Article::class, Source::class],
    version = DATABASE_VERSION,
    exportSchema = false
)
@TypeConverters(SourceConverter::class)
abstract class NewsDatabase : RoomDatabase() {
    companion object {
        private const val DATABASE_NAME = "NewsDB"

        fun buildDatabase(context: Context): NewsDatabase {
            return Room.databaseBuilder(
                context,
                NewsDatabase::class.java,
                DATABASE_NAME
            )
                .allowMainThreadQueries()
                .build()
        }
    }

    abstract fun articleDao(): ArticleDao

    abstract fun articleSourceDao(): ArticleSourceDao

    abstract fun sourceDao(): SourceDao

}
