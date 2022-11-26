package com.example.newsapplication.presentation

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newsapplication.data.NewsRepository
import com.example.newsapplication.data.api.Article
import com.example.newsapplication.data.api.ArticlesArray
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: NewsRepository): ViewModel() {

    val myResponse: MutableLiveData<List<Article>> = MutableLiveData()

    fun getArticle(){
        viewModelScope.launch {
            val response = repository.getArticle()
            if (response.isSuccessful){
                myResponse.value = response.body()?.articles
            }
            else {
                TODO()
            }
        }
    }

}