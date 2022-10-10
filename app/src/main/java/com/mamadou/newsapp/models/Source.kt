package com.mamadou.newsapp.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "sources")
data class Source(
   val id: String? = null,
   @PrimaryKey val name: String,
   val category: CategoryType? = null,
   val language: LanguageType? = null,
   val country: CountryType? = null,
) : Parcelable
