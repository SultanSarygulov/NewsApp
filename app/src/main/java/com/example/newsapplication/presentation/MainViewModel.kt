package com.example.newsapplication.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapplication.data.NewsRepository
import com.example.newsapplication.data.api.Article
import com.example.newsapplication.data.api.ArticlesArray
import kotlinx.coroutines.launch

class MainViewModel(private val repository: NewsRepository): ViewModel() {

    val myResponse: MutableLiveData<List<Article>> = MutableLiveData()

    fun getArticle(){
        viewModelScope.launch {
            val response = repository.getArticle().articles
            myResponse.value = response
        }
    }

}