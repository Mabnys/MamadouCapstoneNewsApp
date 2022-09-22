package com.mamadou.newsapp
import android.content.Context
import android.os.Bundle
import android.content.Intent
import android.net.ConnectivityManager
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.mamadou.newsapp.databinding.ActivityMainBinding
import com.mamadou.newsapp.networking.NetworkStatusChecker
import com.mamadou.newsapp.networking.RemoteApi
import com.mamadou.newsapp.networking.buildApiService
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
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
                if (error != null) {
                    Log.e("There is an error", error.toString())

                } else {
                    binding.articleRecyclerView.run {
                        adapter = ArticleRecyclerAdapter(articles) { articleIndex ->
                            val newsDetailIntent = Intent(this@MainActivity, NewsDetailsActivity::class.java)
                            newsDetailIntent.putExtra(INTENT_EXTRA_ARTICLE, articles[articleIndex])
                            startActivity(newsDetailIntent)
                        }
                    }
                }
            }
        }, {
            Toast.makeText(this@MainActivity, "Disconnected", Toast.LENGTH_LONG).show()
        })
    }

    companion object {
        const val INTENT_EXTRA_ARTICLE = "article"
    }
}
