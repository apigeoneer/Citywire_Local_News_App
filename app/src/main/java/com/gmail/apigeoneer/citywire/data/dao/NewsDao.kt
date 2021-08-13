package com.gmail.apigeoneer.citywire.data.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gmail.apigeoneer.citywire.data.models.Article

@Dao
interface NewsDao {
    @Query("SELECT * FROM articles")
    fun getArticles(): LiveData<List<Article>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg articles: Article)
}