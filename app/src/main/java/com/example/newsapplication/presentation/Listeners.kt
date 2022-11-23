package com.example.newsapplication.presentation

import com.example.newsapplication.data.api.Article

interface Listeners {

    fun readArticle(currentArticle: Article)

}