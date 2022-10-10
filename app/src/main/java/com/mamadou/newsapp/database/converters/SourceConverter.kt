package com.mamadou.newsapp.database.converters

import android.util.Log
import androidx.room.TypeConverter
import androidx.work.ListenableWorker
import com.google.common.reflect.TypeToken
import com.mamadou.newsapp.App
import com.mamadou.newsapp.models.Source
import com.mamadou.newsapp.utils.CustomResult

class SourceConverter {

    @TypeConverter
    fun fromSources(list: Source): String = App.gson.toJson(list)

    @TypeConverter
    fun toSources(json: String): Source {
        val listType = object : TypeToken<Source>() {}.type

//        return try {
            return    App.gson.fromJson(json, listType)

//        } catch (error: Throwable) {
//
////            error.printStackTrace(),
//            CustomResult.Failure,
//        }
    }
}