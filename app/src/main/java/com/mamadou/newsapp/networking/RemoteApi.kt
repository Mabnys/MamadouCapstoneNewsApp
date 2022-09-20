package com.mamadou.newsapp.networking

import com.mamadou.newsapp.models.Article
import com.mamadou.newsapp.models.response.GetNewsResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val BASE_URL ="https://newsapi.org"
const val TOKEN_KEY = "926a9e3d32f545be911ca1e71f9aff72"
class RemoteApi(private val apiService: RemoteApiService) {

    fun getArticles(onArticlesReceived: (List<Article>, Throwable?) -> Unit) {
        apiService.getArticles(TOKEN_KEY).enqueue(object : Callback<GetNewsResponse> {
            override fun onResponse(
                call: Call<GetNewsResponse>,
                response: Response<GetNewsResponse>
            ) {
                val data = response.body()

                if (data != null && data.articles.isNotEmpty()) {
                    println("******* Call Request Received $response *********")
                    println("********* See Articles Below $data **********")
                    println("*********** Number Of Articles are ${response.body()!!.articles.size} ***********")
                    onArticlesReceived(data.articles, null)
                } else {
                    onArticlesReceived(emptyList(), NullPointerException("No news available!"))
                }
            }
            override fun onFailure(call: Call<GetNewsResponse>, error: Throwable) {
                print("********* Call Request Failed **********")
                onArticlesReceived(emptyList(), error)
            }
    })
    }
}