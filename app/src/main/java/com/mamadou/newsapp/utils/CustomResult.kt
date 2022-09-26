package com.mamadou.newsapp.utils

sealed class CustomResult<out T> {
    data class Success<out T>(val value: T) : CustomResult<T>()
    data class Failure(val error: String): CustomResult<Nothing>()
}
