package com.example.newsapplication.data.room

import androidx.lifecycle.LiveData
import com.example.newsapplication.data.api.Article

class SavedArticleRepository(private val savedArticleDao: SavedArticleDao) {

    val savedArticles: LiveData<List<Article>> = savedArticleDao.getSavedArticles()

    suspend fun saveArticle(article: Article){
        savedArticleDao.saveArticle(article)
    }
}