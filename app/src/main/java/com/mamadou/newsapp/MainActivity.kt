package com.mamadou.newsapp
import android.os.Bundle
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.mamadou.newsapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var articles = NewsService().getArticles()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        //first check to see if there is any data stored in the newsDataManager with readNews
        val newsDataManager = NewsDataManager(this)

        var newsFromMemory = newsDataManager.readNews()

        if (newsFromMemory.isEmpty()){
            newsDataManager.saveNews(articles)
            newsFromMemory = newsDataManager.readNews()
        }

        binding.articleRecyclerView.run {
            adapter = ArticleRecyclerAdapter(newsFromMemory) { articleIndex ->
                val newsDetailIntent = Intent(this@MainActivity, NewsDetailsActivity::class.java)
                newsDetailIntent.putExtra("article", newsFromMemory[articleIndex])
                startActivity(newsDetailIntent)
            }
        }
    }
}
