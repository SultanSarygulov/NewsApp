package com.example.newsapplication.data.room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.newsapplication.data.api.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SavedArticleViewModel(application: Application): AndroidViewModel(application) {

    private val repository:SavedArticleRepository
    var savedArticles: LiveData<List<Article>>

    init {
        val articleTableDao = SavedArticleDatabase.getDatabase(application).savedArticleDao()
        repository = SavedArticleRepository(articleTableDao)
        savedArticles = repository.savedArticles
    }

    fun saveArticle(article: Article){
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveArticle(article)
        }
    }
}