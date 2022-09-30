package com.mamadou.newsapp
import android.content.Context
import android.os.Bundle
import android.content.Intent
import android.net.ConnectivityManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.mamadou.newsapp.databinding.ActivityMainBinding
import com.mamadou.newsapp.networking.NetworkStatusChecker
import com.mamadou.newsapp.networking.RemoteApi
import com.mamadou.newsapp.networking.buildApiService
import com.mamadou.newsapp.utils.CustomResult
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val networkStatusChecker by lazy {
        NetworkStatusChecker(this.getSystemService(Context.CONNECTIVITY_SERVICE)  as ConnectivityManager)
    }

    private  val service = buildApiService()
    private  val remoteApi = RemoteApi(service)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.articleRecyclerView.run {
            adapter = articleAdapter
        }
        fetchArticles()
        binding.swiperefresh.setOnRefreshListener {
            fetchArticles()
        }
    }

    private val articleAdapter =
        ArticleRecyclerAdapter { article ->
            val newsDetailIntent = Intent(this@MainActivity, NewsDetailsActivity::class.java)
            newsDetailIntent.putExtra(INTENT_EXTRA_ARTICLE, article)
            startActivity(newsDetailIntent)
        }


    private fun fetchArticles() {
        networkStatusChecker.performIfConnectedToInternet ({
            binding.swiperefresh.isRefreshing = true
            lifecycleScope.launch(IO) {
                val result = remoteApi.getArticles()
                withContext(Main) {
                    when(result) {
                        is CustomResult.Success -> {
                                articleAdapter.updateArticle(result.value)
                        }
                        is CustomResult.Failure -> {
                            Toast.makeText(this@MainActivity, "No Data Available", Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }, {
            Toast.makeText(this@MainActivity, "Disconnected", Toast.LENGTH_LONG).show()
        })
        binding.swiperefresh.isRefreshing = false

    }


    companion object {
        const val INTENT_EXTRA_ARTICLE = "article"
    }
}