package com.mamadou.newsapp.ui
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SwitchCompat
import androidx.compose.material.MaterialTheme
import com.mamadou.newsapp.R
import com.mamadou.newsapp.databinding.ActivityMainBinding
import com.mamadou.newsapp.utils.CustomResult
import com.mamadou.newsapp.viewmodels.MainActivityViewModel
import com.mamadou.newsapp.viewmodels.NewsListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: MainActivityViewModel by viewModels()
    private val viewsModel by viewModels<NewsListViewModel>()

    private val TAG = this.javaClass.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d(TAG, "onCreate(): Current State")

        viewsModel.articles.observe(this) { result ->
            Log.d(TAG, "Here is our current list of news $result")
            when(result) {
                is CustomResult.Success -> {
                    Log.d(TAG, "Updating our news' article")
                    binding.composeView.setContent {
                        MaterialTheme {
                            ArticleListComposable(articles = result.value,
                                clickListener =
                                { article ->
                                    val newsDetailIntent = Intent(this@MainActivity, NewsDetailsActivity::class.java)
                                    newsDetailIntent.putExtra(INTENT_EXTRA_ARTICLE, article)
                                    startActivity(newsDetailIntent)
                                },
                                onRefresh = {
                                    loadingArticles()
                                }
                            )
                        }
                    }
                }
                is CustomResult.Failure -> {
                    Log.e(TAG, "Ops!! No news Available.")
                    Toast.makeText(this@MainActivity, "No Data Available", Toast.LENGTH_LONG).show()
                }
            }
            Toast.makeText(this@MainActivity, "Refreshing...", Toast.LENGTH_LONG).show()

        }
        val queryTextListener = object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
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
        Log.d(TAG, "onCreate(): method finish")

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        val switchItem = menu!!.findItem(R.id.wifi_switch_items)
        val switch = switchItem.actionView?.findViewById<SwitchCompat>(R.id.wifi_switch)
        viewModel.isDownloadOverWifiOnly.observe(this) { isDownloadOverWifiOnly ->
            if (switch != null) {
                isDownloadOverWifiOnly.also { switch.isChecked = it }
            }
            if (isDownloadOverWifiOnly) {
                if (switch != null) {
                    switch.text = getString(R.string.switch_on, switchItem.title)
                    Log.d(TAG, "Wifi is ON, You can download!!!")
                }
            } else {
                if (switch != null) {
                    switch.text = getString(R.string.switch_off, switchItem.title)
                    Log.e(TAG, "Wifi is OFF, Turn it ON before download!!!")
                }
            }
        }
        switch?.setOnCheckedChangeListener { _, _ ->
            viewModel.toggleDownloadOverWifiOnly()

        }
        return true
    }

    private fun loadingArticles() {
        viewsModel.fetchArticles()
    }

    companion object {
        const val INTENT_EXTRA_ARTICLE = "article"
    }
}