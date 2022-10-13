package com.mamadou.newsapp

import android.app.Application
import com.google.gson.Gson
import com.mamadou.newsapp.database.NewsDatabase
import com.mamadou.newsapp.networking.buildApiService
import com.mamadou.newsapp.repository.NewsRepository
import com.mamadou.newsapp.repository.NewsRepositoryImpl

class App : Application() {

    companion object {
        private lateinit var instance: App

        private val database: NewsDatabase by lazy {
            NewsDatabase.buildDatabase(instance)
        }

        private val newsApiService by lazy {
            buildApiService()
        }

        val gson by lazy { Gson() }

        val newsRepository: NewsRepository by lazy {
            NewsRepositoryImpl(
                database.articleDao(),
                database.articleSourceDao(),
                database.sourceDao(),
                newsApiService
            )
        }


    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}