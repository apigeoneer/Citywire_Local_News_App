package com.gmail.apigeoneer.citywire

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.gmail.apigeoneer.citywire.adapters.NewsAdapter
import com.gmail.apigeoneer.citywire.databinding.FragmentNewsBinding
import com.gmail.apigeoneer.citywire.viewmodels.NewsViewModel

class NewsFragment : Fragment() {

    private lateinit var binding: FragmentNewsBinding

    private val _viewModel by viewModels<NewsViewModel>()
    private val _adapter=NewsAdapter(_viewModel.articles.value!!)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding=DataBindingUtil.inflate(inflater, R.layout.fragment_news, container, false)

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner=this

        val layoutManager = LinearLayoutManager(context)

        binding.apply {
            newsViewModel=_viewModel

            newsRecyclerView.adapter=_adapter
            newsRecyclerView.layoutManager = layoutManager
            newsRecyclerView.setHasFixedSize(true)
        }


        return binding.root
    }

}