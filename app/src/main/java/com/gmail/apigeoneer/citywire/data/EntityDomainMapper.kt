package com.gmail.apigeoneer.citywire.data

import com.gmail.apigeoneer.citywire.data.entity.ArticleEntity
import com.gmail.apigeoneer.citywire.data.models.Article

/**
 * Convert database objects -> domain objects
 */
fun List<Article>.toDatabaseModel(): Array<ArticleEntity>{

    return map{
        ArticleEntity(
            author = it.author,
            title = it.title,
            description = it.description,
            url = it.url,
            urlToImage = it.urlToImage,
            publishedAt = it.publishedAt,
            content = it.content,
        )
    }.toTypedArray()
}

/**
 * Convert domain objects -> database objects
 */
fun List<ArticleEntity>.toDomainModel(): List<Article>{
    return map{
        Article(
            author = it.author,
            title = it.title,
            description = it.description,
            url = it.url,
            urlToImage = it.urlToImage,
            publishedAt = it.publishedAt,
            content = it.content,
        )
    }
}