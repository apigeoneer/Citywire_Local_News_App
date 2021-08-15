package com.gmail.apigeoneer.citywire.viewmodels

import android.app.AlarmManager
import android.app.Application
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.SystemClock
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.*
import com.gmail.apigeoneer.citywire.data.db.getDatabase
import com.gmail.apigeoneer.citywire.data.models.Article
import com.gmail.apigeoneer.citywire.data.repository.NewsRepository
import com.gmail.apigeoneer.citywire.utilities.NewsBroadcastReceiver
import com.gmail.apigeoneer.citywire.utilities.sendNotification
import kotlinx.coroutines.launch


@RequiresApi(Build.VERSION_CODES.O)
class NewsViewModel(private val app: Application) : ViewModel() {

//    private var _articles=MutableLiveData<List<Article>>()
//    val articles: LiveData<List<Article>>
//        get()=_articles

    private var _navigateToArticle=MutableLiveData<Article?>()
    val navigateToArticle: LiveData<Article?>
        get()=_navigateToArticle

    val database = getDatabase(app)
    val repository = NewsRepository(database)

    init {
//        getNewsArticles()

        viewModelScope.launch {
            repository.refreshArticles()
        }
    }


//    private fun getNewsArticles() {
////        _response.value = "Set the News API Response here."
//
//        /**
//         * Fetching data from the internet using Retrofit
//         */
////        // call the http query fun in the api interface using the singleton
////        // https://newsapi.org/v2/everything?q=tesla&from=2021-07-10&sortBy=publishedAt&apiKey=API_KEY
////        val news=NewsService.newsApiService.getNews("gorakhpur")     // , R.string.API_KEY.toString()
////
////
////        news.enqueue(object : Callback<News> {
////            override fun onResponse(call: Call<News>, response: Response<News>) {
////                val responseData: News?=response.body()
////                _articles.value=responseData?.articles
////                if (_articles.value == null) {
////                    Log.d(TAG, "api response null")
////                }
////                Log.d(TAG, "::::::: Api Response: ${response.body()?.status} :::::::")
////                Log.d(TAG, "::::::: Api Response: ${_articles.value} :::::::")
////            }
////
////            override fun onFailure(call: Call<News>, t: Throwable) {
////                Log.d(TAG, "::::::: No Api Response: ${t.message} :::::::")
////            }
////        })
//
//        /**
//         *  Fetching data from the internet using Retrofit via coroutine
//         */
//        viewModelScope.launch {
//            val news=NewsService.newsApiService.getNewsAsync("gorakhpur")     // , R.string.API_KEY.toString()
//
//            try {
//                _articles.value=news.articles
//                if (_articles.value == null) {
//                    Log.d(TAG, "api response null")
//                }
//                Log.d(TAG, "::::::: Api Response: ${_articles.value} :::::::")
//
//            } catch (e: Exception) {
//                Log.d(TAG, "::::::: No Api Response: ${e.message} :::::::")
//            }
//        }
//    }

    fun navigateToDetails(article: Article) {
        _navigateToArticle.value=article
    }

    fun displayArticleDetailsComplete() {
        _navigateToArticle.value=null
    }

    companion object {
        private const val TAG="NewsViewModel"
    }

}

class NewsViewModelFactory(
    private val app: Application
) : ViewModelProvider.Factory {

    @RequiresApi(Build.VERSION_CODES.O)
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsViewModel::class.java)) {
            return NewsViewModel(app) as T
        }
        throw IllegalArgumentException("Unable to construct NewsViewModel")
    }
}