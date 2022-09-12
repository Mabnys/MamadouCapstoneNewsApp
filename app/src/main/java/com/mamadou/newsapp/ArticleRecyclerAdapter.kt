package com.mamadou.newsapp

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mamadou.newsapp.models.Article
import com.mamadou.newsapp.views.ArticleView

class ArticleRecyclerAdapter(
    articleList: List<Article>
) : RecyclerView.Adapter<ArticleViewHolder>() {

    private val articles = articleList.toMutableList()

    override  fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val articleView = ArticleView(parent.context)
        articleView.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
        )
        return ArticleViewHolder(articleView)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, positon: Int) {
        holder.bindData(articles[positon]) {
            removeArticleAtIndex(holder.absoluteAdapterPosition)
        }
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    private fun removeArticleAtIndex(index: Int) {
        articles.removeAt(index)
        notifyItemRemoved(index)
    }
}