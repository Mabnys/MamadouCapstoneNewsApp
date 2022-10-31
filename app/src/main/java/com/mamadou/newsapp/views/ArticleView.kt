package com.mamadou.newsapp.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import com.mamadou.newsapp.databinding.ArticleViewBinding
import com.mamadou.newsapp.models.Article
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleView  @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    defStyleRes: Int = 0
) : LinearLayout(context, attrs, defStyleAttr, defStyleRes){

    private val binding = ArticleViewBinding.inflate(LayoutInflater.from(context), this)

    init {
        orientation = VERTICAL
    }

    fun getData(article: Article) {
        setArticleTitle(article.title)
        Glide.with(this)
            .load(article.urlToImage)
            .into(binding.articleUrlImageView)
        setArticleAuthor(article.author)
        article.description?.let { setArticleDescription(it) }
        setArticleUrl(article.url)
        article.publishedAt?.let { setArticlePublishedAt(it) }
        setSourceName(article.source.name)
    }

    private fun setArticleTitle(title: String) {
        binding.articleTitleTextView.text = title
    }

    private fun setArticleAuthor(author: String?) {
        binding.articleAuthorTextView.text = author

    }

    private fun setArticleDescription(description: String) {
        binding.articleDescriptionTextView.text = description
    }

    private fun setArticleUrl(url: String?) {
        binding.articleUrlTextView.text = url
    }

    private fun setArticlePublishedAt(publishAt: String) {
        binding.articlePublishAtTextView.text = publishAt
    }

    private fun setSourceName(sourceName: String) {
        binding.articleSourceNameTextView.text = sourceName
    }
}


