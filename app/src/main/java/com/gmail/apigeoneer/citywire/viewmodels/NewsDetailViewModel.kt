package com.gmail.apigeoneer.citywire.viewmodels

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gmail.apigeoneer.citywire.data.models.Article

class NewsDetailViewModel(
    val article: Article,
    val app: Application
): ViewModel() {

    private var _selectedArticle = MutableLiveData<Article>()
    val selectedArticle: LiveData<Article>
        get()=_selectedArticle


    init {
        _selectedArticle.value = article
    }

}

class NewsDetailViewModelFactory(
    private val article: Article,
    private val app: Application
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NewsDetailViewModel::class.java)) {
            return NewsDetailViewModel(article, app) as T
        }
        throw IllegalArgumentException("Unable to construct NewsDetailViewModel")
    }
}