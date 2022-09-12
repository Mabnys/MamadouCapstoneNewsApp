package com.mamadou.newsapp

import androidx.recyclerview.widget.RecyclerView
import com.mamadou.newsapp.views.ArticleView
import com.mamadou.newsapp.models.Article

class ArticleViewHolder(
    private val articleView: ArticleView
) : RecyclerView.ViewHolder(articleView) {

    fun bindData(article: Article, onDeleteTapped: () -> Unit) {
        articleView.getData(article, onDeleteTapped)
    }
}