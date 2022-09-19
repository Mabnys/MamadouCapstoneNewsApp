package com.mamadou.newsapp.networking

import com.mamadou.newsapp.models.Article
import com.mamadou.newsapp.models.response.GetNewsResponse
import com.mamadou.newsapp.utils.App
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val BASE_URL ="https://newsapi.org"

class RemoteApi(private val apiService: RemoteApiService) {

    fun getArticles(onArticlesReceived: (List<Article>, Throwable?) -> Unit) {
        apiService.getArticles(App.getToken()).enqueue(object : Callback<GetNewsResponse> {
            override fun onResponse(
                call: Call<GetNewsResponse>,
                response: Response<GetNewsResponse>
            ) {
                val data = response.body()

                if (data != null && data.articles.isNotEmpty()) {
                    onArticlesReceived(data.articles, null)
                } else {
                    onArticlesReceived(emptyList(), NullPointerException("No news available!"))
                }
//
            }
            override fun onFailure(call: Call<GetNewsResponse>, error: Throwable) {
                onArticlesReceived(emptyList(), error)
            }
    })


    }

}