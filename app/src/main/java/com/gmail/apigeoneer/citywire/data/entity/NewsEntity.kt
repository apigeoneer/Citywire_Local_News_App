package com.gmail.apigeoneer.citywire.data.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gmail.apigeoneer.citywire.data.models.Article
import kotlinx.parcelize.Parcelize

@Entity(tableName = "news")
@Parcelize
data class News(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val status: String,
    val totalResults: Int,
    val articles: List<Article> = emptyList()
): Parcelable