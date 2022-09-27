package com.mamadou.newsapp.networking

import android.util.Log
import com.mamadou.newsapp.models.Article
import com.mamadou.newsapp.models.response.GetNewsResponse
import com.mamadou.newsapp.utils.CustomResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

const val BASE_URL ="https://newsapi.org"
const val TOKEN_KEY = "926a9e3d32f545be911ca1e71f9aff72"
class RemoteApi(private val apiService: RemoteApiService) {

//    suspend fun getArticles(): Result<List<Article>> = try {
//        val data = apiService.getArticles()
//
//        Success(CustomResult.articles)
//
//
//    }catch (error: Throwable) {
//        Failure(error)
//    }

    suspend fun getArticles(onArticlesReceived: (List<Article>, Throwable?) -> Unit) {
       withContext(Dispatchers.IO) {
    //      try {
           val job = launch {
               apiService.getArticles(TOKEN_KEY).enqueue(object : Callback<GetNewsResponse> {
                   override fun onResponse(
                       call: Call<GetNewsResponse>,
                       response: Response<GetNewsResponse>
                   ) {
                       val data = response.body()

                       if (data != null && data.articles.isNotEmpty()) {
                           Log.i("Call Request Received", response.toString())
                           Log.i("See Articles Below", data.toString())
                           Log.i("Number Of Articles are", response.body()!!.articles.size.toString())
                           onArticlesReceived(data.articles, null)
                       } else {
                           onArticlesReceived(emptyList(), NullPointerException("No news available!"))
                       }
                   }
                   override fun onFailure(call: Call<GetNewsResponse>, error: Throwable) {
                       Log.e("Call Request Failed", error.toString())
                       onArticlesReceived(emptyList(), error)
                   }
               })
           }

    //      } catch (error: Throwable) {
    //          CustomResult.Failure(error)
    //      }

       }



    }
}