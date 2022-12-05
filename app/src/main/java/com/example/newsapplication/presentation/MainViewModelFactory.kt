package com.example.newsapplication.presentation

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.newsapplication.data.NewsRepository
import com.example.newsapplication.presentation.main.MainViewModel

class MainViewModelFactory(private val repository: NewsRepository, private val application: Application?): ViewModelProvider.Factory{

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository, application) as T
    }
}