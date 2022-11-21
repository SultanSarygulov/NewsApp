package com.example.newsapplication.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel(private val repository: NewsRepository): ViewModel() {

    val myResponse: MutableLiveData<Article> = MutableLiveData()

    fun getArticle(){
        viewModelScope.launch {
            val response = repository.getArticle()
            myResponse.value = response
        }
    }

}