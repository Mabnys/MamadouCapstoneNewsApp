package com.mamadou.newsapp.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Source(
   val id: String? = null,
   val name: String,
   val category: CategoryType? = null,
   val language: LanguageType? = null,
   val country: CountryType? = null,
) : Parcelable
