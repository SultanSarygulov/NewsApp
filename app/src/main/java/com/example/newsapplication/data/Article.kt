package com.example.newsapplication.data

data class Article(
    val status: String,
    val urlToImage: String,
    val title: String,
    val content: String,
    val description: String,
    val author: String,
    val publishedAt: String
)