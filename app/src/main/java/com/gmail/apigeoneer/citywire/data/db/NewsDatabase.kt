package com.gmail.apigeoneer.citywire.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gmail.apigeoneer.citywire.data.dao.NewsDao
import com.gmail.apigeoneer.citywire.data.entity.ArticleEntity
import com.gmail.apigeoneer.citywire.data.models.Article

@Database(entities = [ArticleEntity::class], version = 2)
abstract class NewsDatabase: RoomDatabase() {
    abstract val newsDao: NewsDao
}

// var for our singleton
private lateinit var INSTANCE: NewsDatabase

fun getDatabase(context: Context): NewsDatabase {
    // Make code synchronized so it's thread safe
    synchronized(NewsDatabase::class.java) {
        // Check whether the database has been initialized
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext, NewsDatabase::class.java,
                "articles").build()
        }
    }
    return INSTANCE
}


