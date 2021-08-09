package com.gmail.apigeoneer.citywire

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.gmail.apigeoneer.citywire.api.NewsService
import com.gmail.apigeoneer.citywire.data.models.Article
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        getNewsArticles()

    }

    private fun getNewsArticles() {
        // call the http query fun in the api interface via the singleton
        val allArticles = NewsService.newsApiService.getNews("gorakhpur",
            "2021-8-3", "2021-8-9","popularity", getString(R.string.API_KEY))

        allArticles.enqueue(object: Callback<Article> {
            override fun onResponse(call: Call<Article>, response: Response<Article>) {
                Log.d(TAG, "::::::: Api Response: $response :::::::")
            }

            override fun onFailure(call: Call<Article>, t: Throwable) {
                Log.d(TAG, "::::::: No Api Response: $t :::::::")
            }

        })
    }

    companion object {
        private const val TAG="MainActivity"
    }


}