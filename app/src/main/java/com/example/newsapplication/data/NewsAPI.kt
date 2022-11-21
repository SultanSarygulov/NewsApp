package com.example.newsapplication.data

import retrofit2.http.GET

interface NewsAPI {

    @GET("v2/everything?q=apple&sortBy=publishedAt&pageSize=1&apiKey=af49d248b01a4025baabaf169e10559a")
    suspend fun getArticle(): Article
}