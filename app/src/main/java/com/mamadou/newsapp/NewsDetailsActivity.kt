package com.mamadou.newsapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mamadou.newsapp.databinding.ActivityNewsDetailsBinding
import com.mamadou.newsapp.models.Article

class NewsDetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding =  ActivityNewsDetailsBinding.inflate(layoutInflater)

        setContentView(binding.root)

        val article =  intent.extras?.getParcelable<Article>("article")

        if (article != null) {
            binding.articleTitleTextView.text = article.title
            binding.articleAuthorTextView.text = article.author
            binding.articleDescriptionTextView.text = article.description
            binding.articleUrlTextView.text = article.url
            binding.articlePublishAtTextView.text = article.publishedAt
            binding.articleSourceNameTextView.text = article.source.name
        }
    }
}