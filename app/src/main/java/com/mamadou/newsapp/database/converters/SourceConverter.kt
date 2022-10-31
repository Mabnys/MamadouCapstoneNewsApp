package com.mamadou.newsapp.database.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.mamadou.newsapp.models.Source


class SourceConverter {
    val gson by lazy { Gson() }

    @TypeConverter
    fun fromSources(source: Source): String = gson.toJson(source)

    @TypeConverter
    fun toSources(json: String): Source = gson.fromJson(json, Source::class.java)
}