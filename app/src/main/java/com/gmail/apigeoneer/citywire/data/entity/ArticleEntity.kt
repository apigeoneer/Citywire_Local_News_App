package com.gmail.apigeoneer.citywire.data.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "articles")
@Parcelize
data class ArticleEntity constructor(
    val author: String? = "",
    @PrimaryKey()
    val title: String = "",
    val description: String = "",
    val url: String = "",
    val urlToImage: String = "",
    val publishedAt: String = "",
    val content: String = ""
): Parcelable