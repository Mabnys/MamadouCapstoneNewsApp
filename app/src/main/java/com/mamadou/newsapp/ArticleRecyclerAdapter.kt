package com.mamadou.newsapp

import android.annotation.SuppressLint
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mamadou.newsapp.models.Article
import com.mamadou.newsapp.views.ArticleView

class ArticleRecyclerAdapter(
    private val onArticleTap: (Article) -> Unit
) : RecyclerView.Adapter<ArticleViewHolder>() {

    private var articles = mutableListOf<Article>()

    override  fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val articleView = ArticleView(parent.context)

        articleView.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
        )
        return ArticleViewHolder(articleView)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, positon: Int) {
        holder.bindData(articles[positon]){
            onArticleTap(articles[positon])
        }
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateArticle(newArticle: List<Article>) {
        articles = newArticle.toMutableList()
        notifyDataSetChanged()
    }

}