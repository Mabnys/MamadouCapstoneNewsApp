package com.mamadou.newsapp.models.relations


import android.os.Parcelable
import androidx.room.Embedded
import androidx.room.Relation
import com.mamadou.newsapp.models.Article
import com.mamadou.newsapp.models.Source
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArticleSource(
    @Embedded
    val article: Article,
    @Relation(
        parentColumn = "title",
        entityColumn = "name"
    )
    val source: Source

) : Parcelable
