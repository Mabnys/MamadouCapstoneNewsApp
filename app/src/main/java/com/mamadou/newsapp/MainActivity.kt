package com.mamadou.newsapp
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mamadou.newsapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var articles = NewsService().getArticles()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.articleRecyclerView.run {
            adapter = ArticleRecyclerAdapter(articles)
        }
    }
}
