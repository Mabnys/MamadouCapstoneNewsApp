package com.mamadou.newsapp
import android.content.Context
import android.os.Bundle
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import com.mamadou.newsapp.databinding.ActivityMainBinding
import com.mamadou.newsapp.networking.NetworkStatusChecker
import com.mamadou.newsapp.networking.RemoteApi
import com.mamadou.newsapp.networking.buildApiService

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val networkStatusChecker by lazy {
        NetworkStatusChecker(this.getSystemService(Context.CONNECTIVITY_SERVICE)  as ConnectivityManager)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        networkStatusChecker.performIfConnectedToInternet ({
            val service = buildApiService()
            val remoteApi = RemoteApi(service)
            remoteApi.getArticles { articles, error ->
                //need to check article is not empty
//                if (error != null) {
//
//                }else {
//
//                }

                binding.articleRecyclerView.run {
                    adapter = ArticleRecyclerAdapter(articles) { articleIndex ->
                        val newsDetailIntent = Intent(this@MainActivity, NewsDetailsActivity::class.java)
                        newsDetailIntent.putExtra(INTENT_EXTRA_ARTICLE, articles[articleIndex])
                        startActivity(newsDetailIntent)
                    }
                }
            }
        }, {
            //Logic for not connected

        })
    }

    companion object {
        const val INTENT_EXTRA_ARTICLE = "article"
    }
}
