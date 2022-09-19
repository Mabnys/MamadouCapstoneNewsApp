package com.mamadou.newsapp.models

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
data class Source(
    @field:Json(name = "id") val id: String? = null,
    @field:Json(name = "name") val name: String,
    val category: CategoryType? = null,
    val language: LanguageType? = null,
    val country: CountryType? = null,
) : Parcelable
