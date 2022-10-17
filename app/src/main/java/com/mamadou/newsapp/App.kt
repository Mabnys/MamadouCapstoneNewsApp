package com.mamadou.newsapp

import android.app.Application
import android.net.ConnectivityManager
import androidx.datastore.preferences.preferencesDataStore
import com.google.gson.Gson
import com.mamadou.newsapp.database.NewsDatabase
import com.mamadou.newsapp.networking.NetworkStatusChecker
import com.mamadou.newsapp.networking.buildApiService
import com.mamadou.newsapp.preferences.PreferencesDataStoreImpl
import com.mamadou.newsapp.repository.NewsRepository
import com.mamadou.newsapp.repository.NewsRepositoryImpl

class App : Application() {
    val dataStore by preferencesDataStore(name = "preferences")

    companion object {

        private lateinit var instance: App

        private val database: NewsDatabase by lazy {
            NewsDatabase.buildDatabase(instance)
        }

        private val newsApiService by lazy {
            buildApiService()
        }

        private val networkStatusChecker by lazy {
            NetworkStatusChecker(instance.getSystemService(ConnectivityManager::class.java))
        }

        val gson by lazy { Gson() }

        val prefsDataStore by lazy { PreferencesDataStoreImpl(instance.dataStore) }


        val newsRepository: NewsRepository by lazy {
            NewsRepositoryImpl(
                database.articleDao(),
                database.articleSourceDao(),
                database.sourceDao(),
                newsApiService,
                prefsDataStore,
                networkStatusChecker
            )
        }
    }




    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}