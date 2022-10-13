package com.mamadou.newsapp
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.mamadou.newsapp.databinding.ActivityMainBinding
import com.mamadou.newsapp.utils.CustomResult
import com.mamadou.newsapp.viewmodels.NewsListViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
//    private val networkStatusChecker by lazy {
//        NetworkStatusChecker(this.getSystemService(Context.CONNECTIVITY_SERVICE)  as ConnectivityManager)
//    }
//
//    private  val service = buildApiService()
//    private  val remoteApi = RemoteApi(service)

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

    private val viewModel by viewModels<NewsListViewModel> {
        NewsListViewModel.Factory(
            newsRepo = App.newsRepository
        )
    }



    private fun fetchArticles() {
   //     networkStatusChecker.performIfConnectedToInternet ({
            binding.swiperefresh.isRefreshing = true
        //    lifecycleScope.launch(IO) {
        viewModel.articles.observe(this) { result ->
//                val result = remoteApi.getArticles()
             //   withContext(Main) {
                    when(result) {
                        is CustomResult.Success -> {
                                articleAdapter.updateArticle(result.value)
                        }
                        is CustomResult.Failure -> {
                            Toast.makeText(this@MainActivity, "No Data Available", Toast.LENGTH_LONG).show()
                        }
                    }
              //  }
            }
//        }, {
//            Toast.makeText(this@MainActivity, "Disconnected", Toast.LENGTH_LONG).show()
        // println("Make sure you're connected to the Internet")
//        })
        binding.swiperefresh.isRefreshing = false

    }


    companion object {
        const val INTENT_EXTRA_ARTICLE = "article"
    }
}