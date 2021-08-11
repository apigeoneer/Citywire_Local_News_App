package com.gmail.apigeoneer.citywire

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.gmail.apigeoneer.citywire.api.NewsService
import com.gmail.apigeoneer.citywire.data.models.Article
import com.gmail.apigeoneer.citywire.databinding.ActivityNewsBinding
import com.gmail.apigeoneer.citywire.viewmodels.NewsViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NewsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_news)

    }

    companion object {
        private const val TAG="MainActivity"
    }


}