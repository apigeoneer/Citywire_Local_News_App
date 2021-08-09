package com.gmail.apigeoneer.citywire.api

import com.gmail.apigeoneer.citywire.data.models.Article
import com.gmail.apigeoneer.citywire.utilities.BASE_URL
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    /**
     * https://newsapi.org/
     * v2/everything?
     * q=gorakhpur &
     * from=2021-08-08 &
     * to=2021-08-08 &
     * sortBy=popularity &
     * apiKey=API_KEY
     */

    @GET("v2/everything?")
    fun getNews(
        @Query("q") query: String,
        @Query("from") from: String,
        @Query("to") to: String,
        @Query("sortBy") sortBy: String,
        @Query("apiKey") apiKey: String
    ) : Call<Article>

}

val retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

object NewsService {
    val newsApiService: NewsApiService by lazy {
        retrofit.create(NewsApiService::class.java)
    }
}