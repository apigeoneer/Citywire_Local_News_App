package com.gmail.apigeoneer.citywire.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gmail.apigeoneer.citywire.data.models.Article
import com.gmail.apigeoneer.citywire.databinding.NewsItemBinding

class NewsAdapter(
    private val articlesList: List<Article>
): RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = NewsItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article = articlesList[position]
        holder.bind(article)
    }

    override fun getItemCount(): Int = articlesList.size

    inner class NewsViewHolder(private val binding: NewsItemBinding)
        : RecyclerView.ViewHolder(binding.root) {
        fun bind(article: Article) {
            binding.article = article
        }
    }
}