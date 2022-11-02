package com.mamadou.newsapp.ui

import androidx.recyclerview.widget.RecyclerView
import com.mamadou.newsapp.models.Article
import com.mamadou.newsapp.views.ArticleView

class ArticleViewHolder(
    private val articleView: ArticleView
) : RecyclerView.ViewHolder(articleView) {

    fun bindData(article: Article, onTap: () -> Unit ) {
        articleView.getData(article)

        articleView.setOnClickListener{
            onTap()
        }
    }
}