package com.gmail.apigeoneer.citywire.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gmail.apigeoneer.citywire.data.models.Article

class NewsDetailViewModel(val article: Article): ViewModel() {

    private var _selectedArticle = MutableLiveData<Article>()
    val selectedArticle: LiveData<Article>
        get()=_selectedArticle


    init {
        _selectedArticle.value = article
    }


}