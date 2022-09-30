package com.mamadou.newsapp.networking

import com.mamadou.newsapp.models.Article
import com.mamadou.newsapp.utils.CustomResult
import java.lang.Exception

const val BASE_URL ="https://newsapi.org"
const val TOKEN_KEY = "926a9e3d32f545be911ca1e71f9aff72"
class RemoteApi(private val apiService: RemoteApiService) {
    suspend fun getArticles() : CustomResult<List<Article>> {
        return  try {
            val data = apiService.getArticles(TOKEN_KEY)
            if (data.articles.isNotEmpty()) {
                CustomResult.Success(data.articles)
            } else {
                CustomResult.Failure(Throwable("No news available!"))
            }

        } catch (e: Exception) {
            CustomResult.Failure(e)
        }
    }
}