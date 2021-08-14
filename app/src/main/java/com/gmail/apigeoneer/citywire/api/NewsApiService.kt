package com.gmail.apigeoneer.citywire.api

import com.gmail.apigeoneer.citywire.data.models.Article
import com.gmail.apigeoneer.citywire.data.models.News
import com.gmail.apigeoneer.citywire.utilities.BASE_URL
import kotlinx.coroutines.Deferred
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val KEY = "a1165ec873794ad6a7711b399b973356"

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

    @GET("everything?apiKey=$KEY")
    suspend fun getNewsAsync(
        @Query("q") q: String,
//        @Query("apiKey") apiKey: String
    ) : News

}

val retrofit: Retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(GsonConverterFactory.create())
    .build()

object NewsService {
    val newsApiService: NewsApiService by lazy { retrofit.create(NewsApiService::class.java) }
}