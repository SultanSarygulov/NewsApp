package com.example.newsapplication.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newsapplication.data.NewsRepository
import com.example.newsapplication.presentation.main.MainViewModel

class MainViewModelFactory(private val repository: NewsRepository): ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}