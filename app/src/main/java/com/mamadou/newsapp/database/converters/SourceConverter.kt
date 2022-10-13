package com.mamadou.newsapp.database.converters

import androidx.room.TypeConverter
import com.mamadou.newsapp.App
import com.mamadou.newsapp.models.Source


class SourceConverter {

    @TypeConverter
    fun fromSources(source: Source): String = App.gson.toJson(source)

    @TypeConverter
    fun toSources(json: String): Source = App.gson.fromJson(json, Source::class.java)
}