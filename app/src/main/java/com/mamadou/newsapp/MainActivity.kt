package com.mamadou.newsapp
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.mamadou.newsapp.databinding.ActivityMainBinding
import com.mamadou.newsapp.utils.CustomResult
import com.mamadou.newsapp.viewmodels.MainActivityViewModel
import com.mamadou.newsapp.viewmodels.NewsListViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
//    private val networkStatusChecker by lazy {
//        NetworkStatusChecker(this.getSystemService(Context.CONNECTIVITY_SERVICE)  as ConnectivityManager)
//    }
//
//    private  val service = buildApiService()
//    private  val remoteApi = RemoteApi(service)
    private val viewModel: MainActivityViewModel by viewModels {
        MainActivityViewModel.Factory()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.articleRecyclerView.run {
            adapter = articleAdapter

        }
        fetchArticles()

        val queryTextListener = object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // do nothing, search is done on text changes
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                newText?.let { searchQuery ->
                    viewsModel.searchArticles(searchQuery)
                }
                return true
            }
        }

        binding.searchView.setOnQueryTextListener(queryTextListener)


        binding.swiperefresh.setOnRefreshListener {
            fetchArticles()
        }
    }

//    override fun onViewCreated(view: View, saveInstanceState: Bundle?) {
//        super.onViewCreated(view, saveInstanceState)
//
//    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.wifi_switch) {


        }
        return true
    }

//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.menu, menu)
//        val switchItem = menu!!.findItem(R.id.wifi_switch_items)
//        val switch = switchItem.actionView?.findViewById<SwitchCompat>(R.id.wifi_switch)
//        viewModel.isDownloadOverWifiOnly.observe(this) { isDownloadOverWifiOnly ->
//            if (switch != null) {
//                isDownloadOverWifiOnly.also { switch.isChecked = it }
//            }
//            if (isDownloadOverWifiOnly) {
//                if (switch != null) {
//                    switch.text = getString(R.string.switch_on, switchItem.title)
//                }
//            } else {
//                if (switch != null) {
//                    switch.text = getString(R.string.switch_off, switchItem.title)
//                }
//            }
//        }
//        switch?.setOnCheckedChangeListener { _, _ ->
//            viewModel.toggleDownloadOverWifiOnly()
//
//        }
//        return true
//    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        if (item.itemId != R.id.wifi_switch) return true
//        return true
//    }

    private val articleAdapter =
        ArticleRecyclerAdapter { article ->
            val newsDetailIntent = Intent(this@MainActivity, NewsDetailsActivity::class.java)
            newsDetailIntent.putExtra(INTENT_EXTRA_ARTICLE, article)
            startActivity(newsDetailIntent)
        }

    private val viewsModel by viewModels<NewsListViewModel> {
        NewsListViewModel.Factory(
            newsRepo = App.newsRepository
        )
    }



    private fun fetchArticles() {
   //     networkStatusChecker.performIfConnectedToInternet ({
            binding.swiperefresh.isRefreshing = true
        //    lifecycleScope.launch(IO) {
        viewsModel.articles.observe(this) { result ->
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