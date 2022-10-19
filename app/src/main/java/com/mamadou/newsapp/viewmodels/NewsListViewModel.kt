package com.mamadou.newsapp.viewmodels

import android.util.Log
import androidx.lifecycle.*
import com.mamadou.newsapp.models.Article
import com.mamadou.newsapp.repository.NewsRepository
import com.mamadou.newsapp.utils.CustomResult
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class NewsListViewModel(
    private val newsRepo: NewsRepository
) : ViewModel() {

    class Factory(
        private val newsRepo: NewsRepository,
    ): ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return NewsListViewModel(newsRepo) as T
        }
    }

    private val TAG = this.javaClass.simpleName

    init {
        fetchArticles()
        Log.d(TAG,"Here are the list of articles")
    }

    fun fetchArticles() {
        viewModelScope.launch(IO) {
            newsRepo
                .getArticles()
                .onEach { newArticle ->
                    _articles.postValue(newArticle)
                    Log.d(TAG, newArticle.toString())
                }
                .collect()
        }
    }


    fun searchArticles(search: String) {
        viewModelScope.launch(IO) {
            val filteredArticles = newsRepo.searchArticles("%$search%")
            _articles.postValue(CustomResult.Success(filteredArticles))
        }
    }

    private val _articles = MutableLiveData<CustomResult<List<Article>>>()
    val articles: LiveData<CustomResult<List<Article>>> = _articles

}



