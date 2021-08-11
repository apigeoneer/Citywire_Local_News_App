package com.gmail.apigeoneer.citywire.data.models


data class News(
    val status: String,
    val totalResults: Int,
    val articles: List<Article> = emptyList()
)