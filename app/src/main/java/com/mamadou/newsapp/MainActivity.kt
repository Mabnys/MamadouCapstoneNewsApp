package com.mamadou.newsapp
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.mamadou.newsapp.databinding.ActivityMainBinding
import com.mamadou.newsapp.views.ArticleView

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var articles = NewsService().getArticles()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        articles.forEach { article ->
            val articleNewsView = ArticleView(this)
            articleNewsView.getData(article)
            binding.mainGroup.addView(articleNewsView)


        }
    }
}
