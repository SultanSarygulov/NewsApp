package com.example.newsapplication.data

class NewsRepository {

    suspend fun getArticle(): Article{
        return RetrofitInstance.api.getArticle()
    }

}