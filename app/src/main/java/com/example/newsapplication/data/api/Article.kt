package com.example.newsapplication.data.api

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "article_table")
data class Article(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    var title: String,
    var author: String,
    var content: String,
    var description: String,
    var publishedAt: String,
    var url: String,
    var urlToImage: String
): Parcelable