package com.gmail.apigeoneer.citywire.viewmodels

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gmail.apigeoneer.citywire.NewsActivity
import com.gmail.apigeoneer.citywire.R
import com.gmail.apigeoneer.citywire.api.NewsService
import com.gmail.apigeoneer.citywire.data.models.Article
import com.gmail.apigeoneer.citywire.data.models.News
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel: ViewModel() {

    private var _articles = MutableLiveData<List<Article>>()

    val articles: LiveData<List<Article>>
        get() = _articles

    init {
        getNewsArticles()
    }


    private fun getNewsArticles() {
//        _response.value = "Set the News API Response here."

        // call the http query fun in the api interface using the singleton
        // https://newsapi.org/v2/everything?q=tesla&from=2021-07-10&sortBy=publishedAt&apiKey=API_KEY
        val news = NewsService.newsApiService.getNews("gorakhpur")     // , R.string.API_KEY.toString()


        news.enqueue(object: Callback<News> {
            override fun onResponse(call: Call<News>, response: Response<News>) {
                val responseData: News?= response.body()
                _articles.value = responseData?.articles
                Log.d(TAG, "::::::: Api Response: ${response.body()?.status} :::::::")
            }

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d(TAG, "::::::: No Api Response: ${t.message} :::::::")
            }
        })
    }

    companion object {
        private const val TAG="NewsViewModel"
    }


}