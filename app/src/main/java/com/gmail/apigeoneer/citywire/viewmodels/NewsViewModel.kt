package com.gmail.apigeoneer.citywire.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import com.gmail.apigeoneer.citywire.NewsActivity
import com.gmail.apigeoneer.citywire.R
import com.gmail.apigeoneer.citywire.api.NewsService
import com.gmail.apigeoneer.citywire.data.models.Article
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsViewModel: ViewModel() {

    fun getNewsArticles() {
        // call the http query fun in the api interface via the singleton
        val allArticles = NewsService.newsApiService.getNews("gorakhpur",
            "2021-8-3", "2021-8-9","popularity", R.string.API_KEY.toString())

        allArticles.enqueue(object: Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                Log.d(TAG, "::::::: Api Response: ${response.body()} :::::::")
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.d(TAG, "::::::: No Api Response: $t :::::::")
            }

        })
    }

    companion object {
        private const val TAG="NewsViewModel"
    }


}