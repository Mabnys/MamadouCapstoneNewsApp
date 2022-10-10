package com.mamadou.newsapp

import android.app.Application
import com.google.gson.Gson
import com.mamadou.newsapp.database.NewsDatabase
import com.mamadou.newsapp.repository.NewsRepository
import com.mamadou.newsapp.repository.NewsRepositoryImpl

class App : Application() {

    companion object {
        private lateinit var instance: App

        private val database: NewsDatabase by lazy {
            NewsDatabase.buildDatabase(instance)

        }

        val repository: NewsRepository by lazy {
            NewsRepositoryImpl(
                database.ArticleDao(),
                database.ArticleSourceDao(),
                database.SourceDao()
            )
        }

        val gson by lazy { Gson() }



//        override fun onCreate() {
//            super.onCreate()
//            instance = this
//        }

    }

}