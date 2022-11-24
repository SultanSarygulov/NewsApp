package com.example.newsapplication.data

import com.example.newsapplication.data.api.Article
import com.example.newsapplication.data.api.ArticlesArray
import retrofit2.Response

class NewsRepository {

    suspend fun getArticle(): Response<ArticlesArray> {
        return RetrofitInstance.api.getArticle()
    }

}