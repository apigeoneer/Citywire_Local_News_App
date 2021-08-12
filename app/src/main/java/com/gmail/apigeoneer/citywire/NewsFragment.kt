package com.gmail.apigeoneer.citywire

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
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

    var articleList: List<Article> = listOf(Article(Source("", ""), "", "", "", "", "", "", ""))

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

        Log.d(TAG, "::::::: _viewModel.articles.value : $articleList :::::::")

        binding.newsRecyclerView.layoutManager = linearLayoutManager
        binding.newsRecyclerView.setHasFixedSize(true)

        _viewModel.articles.observe(viewLifecycleOwner, Observer { articles ->
            if (articles != null) {
                // Set the RecyclerView here
                articleList=_viewModel.articles.value!!

                _adapter = NewsAdapter(articleList)
                binding.newsRecyclerView.adapter=_adapter
                _adapter.notifyDataSetChanged()
            }
        })

        return binding.root
    }

    companion object {
        private const val TAG="NewsFragment"
    }

}