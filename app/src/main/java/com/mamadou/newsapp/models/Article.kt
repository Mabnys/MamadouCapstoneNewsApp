package com.mamadou.newsapp.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.mamadou.newsapp.database.converters.SourceConverter
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "articles")
data class Article(
    val author: String? = null,
    @PrimaryKey val title: String,
    val description: String? = null,
    val url: String? = null,
    val urlToImage: String? = null,
    val publishedAt: String? = null,
    val content: String? = null,
    @TypeConverters(SourceConverter::class)
    val source: Source,
) : Parcelable



