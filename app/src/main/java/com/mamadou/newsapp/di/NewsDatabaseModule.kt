package com.mamadou.newsapp.di

import android.content.Context
import com.mamadou.newsapp.database.NewsDatabase
import com.mamadou.newsapp.database.dao.ArticleDao
import com.mamadou.newsapp.database.dao.ArticleSourceDao
import com.mamadou.newsapp.database.dao.SourceDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NewsDatabaseModule {
    @Singleton
    @Provides
    fun provideNewsDatabase(@ApplicationContext context: Context): NewsDatabase {
        return NewsDatabase.buildDatabase(context)
    }

    @Provides
    fun provideArticleDao(newsDatabase: NewsDatabase): ArticleDao {
        return newsDatabase.articleDao()
    }

    @Provides
    fun provideArticleSourceDao(newsDatabase: NewsDatabase): ArticleSourceDao {
        return newsDatabase.articleSourceDao()
    }

    @Provides
    fun provideSourceDao(newsDatabase: NewsDatabase): SourceDao {
        return newsDatabase.sourceDao()
    }
}
