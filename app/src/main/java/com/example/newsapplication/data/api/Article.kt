package com.example.newsapplication.data.api

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Article(
    var author: String,
    var content: String,
    var description: String,
    var publishedAt: String,
    var title: String,
    var url: String,
    var urlToImage: String
): Parcelable