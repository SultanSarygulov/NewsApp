package com.example.newsapplication.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.newsapplication.data.api.Article

@Database(entities = [Article::class], version = 1, exportSchema = false)
abstract class SavedArticleDatabase: RoomDatabase() {

    abstract fun savedArticleDao(): SavedArticleDao

    companion object{
        @Volatile
        private var INSTANCE: SavedArticleDatabase? = null

        fun getDatabase(context: Context): SavedArticleDatabase{
            val tempInstance = INSTANCE

            if (tempInstance != null){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    SavedArticleDatabase::class.java,
                    "saved_article_database"
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}