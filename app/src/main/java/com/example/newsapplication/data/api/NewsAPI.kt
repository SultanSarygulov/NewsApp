package com.example.newsapplication.data.api

import retrofit2.Response
import retrofit2.http.GET

interface NewsAPI {

    @GET("v2/everything?q=apple&sortBy=publishedAt&pageSize=20&language=en&apiKey=af49d248b01a4025baabaf169e10559a")
    suspend fun getArticle(): Response<ArticlesArray>

//    @GET("v2/everything?q=apple&sortBy=publishedAt&pageSize=20&language=en&apiKey=af49d248b01a4025baabaf169e10559a")
//    suspend fun searchArticles(query: String): ArticlesArray
}