package com.gmail.apigeoneer.citywire.data.models

data class Article(
    val source: List<Source>,
    val author: String,
    val title: String,
    val description: String,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val content: String
)
