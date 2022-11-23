package com.example.newsapplication.data.api

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ArticlesArray(
    val articles: List<Article>
): Parcelable