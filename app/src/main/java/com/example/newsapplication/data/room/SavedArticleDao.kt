package com.example.newsapplication.data.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newsapplication.data.api.Article
import com.example.newsapplication.data.api.ArticlesArray

@Dao
interface SavedArticleDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun saveArticle(article: Article)

    @Query("SELECT * FROM article_table")
    fun getSavedArticles(): LiveData<List<Article>>

}