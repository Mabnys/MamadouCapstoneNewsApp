package com.mamadou.newsapp.ui

import android.content.Context
import android.preference.PreferenceManager
import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import com.mamadou.newsapp.models.Article

class NewsDataManager (private val context: Context) {

    fun saveNews(list:List<Article>){
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context)
        val gson = Gson()

        sharedPrefs.edit().putString(PREFS_ARTICLES_KEY,gson.toJson(list)).commit()

    }

    fun readNews(): List<Article>{
        val sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context)

        val storedArticles = sharedPrefs.getString("storeArticles", "")

        if (storedArticles.isNullOrEmpty() ){
            return emptyList()
        }
        val gson = Gson()

        return gson.fromJson(storedArticles, object : TypeToken<List<Article>>() {}.type)

    }

    companion object {
        const val PREFS_ARTICLES_KEY = "storeArticles"
    }
}

