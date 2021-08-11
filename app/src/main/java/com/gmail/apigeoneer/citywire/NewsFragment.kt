package com.gmail.apigeoneer.citywire

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.gmail.apigeoneer.citywire.adapters.NewsAdapter
import com.gmail.apigeoneer.citywire.data.models.Article
import com.gmail.apigeoneer.citywire.data.models.Source
import com.gmail.apigeoneer.citywire.databinding.FragmentNewsBinding
import com.gmail.apigeoneer.citywire.viewmodels.NewsViewModel

class NewsFragment : Fragment() {

    private lateinit var binding: FragmentNewsBinding

    private val _viewModel by viewModels<NewsViewModel>()
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var _adapter: NewsAdapter

    var articles: List<Article> = listOf(Article(Source("", ""), "", "", "", "", "", "", ""))

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding=DataBindingUtil.inflate(inflater, R.layout.fragment_news, container,false)

        linearLayoutManager = LinearLayoutManager(context)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner=this
        binding.newsViewModel=_viewModel

        if (articles != null) {
            articles=_viewModel.articles.value!!
        }
        Log.d(TAG, "::::::: _viewModel.articles.value : $articles :::::::")
        _adapter = NewsAdapter(articles)

//        if (articles == null) {
//            articles = listOf(Article(Source("", ""), "", "", "", "", "", "", ""))
//        }

        setupUI()

        return binding.root
    }

    private fun setupUI() {
        binding.newsRecyclerView.layoutManager = linearLayoutManager
        binding.newsRecyclerView.setHasFixedSize(true)
        binding.newsRecyclerView.adapter=_adapter
        _adapter.notifyDataSetChanged()

    }


    companion object {
        private const val TAG="NewsFragment"
    }

}