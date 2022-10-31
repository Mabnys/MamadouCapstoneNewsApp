package com.mamadou.newsapp.di

import com.mamadou.newsapp.database.dao.ArticleDao
import com.mamadou.newsapp.database.dao.ArticleSourceDao
import com.mamadou.newsapp.database.dao.SourceDao
import com.mamadou.newsapp.networking.NetworkStatusChecker
import com.mamadou.newsapp.networking.RemoteApiService
import com.mamadou.newsapp.preferences.PreferencesDataStore
import com.mamadou.newsapp.repository.NewsRepository
import com.mamadou.newsapp.repository.NewsRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NewsRepositoryModule {

    @Singleton
    @Provides
    fun provideNewsRepository(
        articleDao: ArticleDao,
        articleSourceDao: ArticleSourceDao,
        sourceDao: SourceDao,
        newApiService: RemoteApiService,
        dataStore: PreferencesDataStore,
        networkStatusChecker: NetworkStatusChecker

    ): NewsRepository {
        return NewsRepositoryImpl(
            articleDao,
            articleSourceDao,
            sourceDao,
            newApiService,
            dataStore,
            networkStatusChecker

        )
    }
}