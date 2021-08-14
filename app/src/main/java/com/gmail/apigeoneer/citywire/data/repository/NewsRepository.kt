package com.gmail.apigeoneer.citywire.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.gmail.apigeoneer.citywire.api.NewsService
import com.gmail.apigeoneer.citywire.data.db.NewsDatabase
import com.gmail.apigeoneer.citywire.data.models.Article
import com.gmail.apigeoneer.citywire.data.toDatabaseModel
import com.gmail.apigeoneer.citywire.data.toDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.json.JSONObject

class NewsRepository(private val database: NewsDatabase) {

    // Convert your LiveData list of DatabaseVideo objects to domain Video objects
    @RequiresApi(Build.VERSION_CODES.O)
    val articles: LiveData<List<Article>> = Transformations.map(database.newsDao.getArticles()
        ) {
            it.toDomainModel()
        }

    /**
     * Update the offline cache
     */
    @RequiresApi(Build.VERSION_CODES.O)
    suspend fun refreshArticles() {
        /**
         * Make a network call to getAsteroids(), & use the await() function
         * to tell the coroutine to suspend until the data is available.
         * Then call insertAll() to insert the asteroids into the database.
         * * -> spread operator: allows you to pass in an array to a fun that expects varargs.
         */

        withContext(Dispatchers.IO) {
//            val articleList = NewsService.newsApiService.getNewsAsync("gorakhpur").await().articles
            val articleList = NewsService.newsApiService.getNewsAsync("gorakhpur").articles

            database.newsDao.insertAll(*articleList.toDatabaseModel())
        }
    }

}