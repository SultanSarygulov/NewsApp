package com.example.newsapplication.presentation

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapplication.data.NewsRepository
import com.example.newsapplication.data.api.Article
import com.example.newsapplication.data.api.ArticlesArray
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: NewsRepository): ViewModel() {

    val myResponse: MutableLiveData<Response<ArticlesArray>> = MutableLiveData()

    fun getArticle(){
        viewModelScope.launch {
            val response = repository.getArticle()
            myResponse.value = response
        }
    }

}