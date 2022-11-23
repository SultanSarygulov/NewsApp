package com.example.newsapplication.data

import com.example.newsapplication.data.api.ArticlesArray

class NewsRepository {

    suspend fun getArticle(): ArticlesArray {
        return RetrofitInstance.api.getArticle()
    }

}