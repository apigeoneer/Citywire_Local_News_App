package com.gmail.apigeoneer.citywire

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.gmail.apigeoneer.citywire.databinding.FragmentNewsDetailBinding
import com.gmail.apigeoneer.citywire.viewmodels.NewsDetailViewModel
import com.gmail.apigeoneer.citywire.viewmodels.NewsDetailViewModelFactory

class NewsDetailFragment : Fragment() {

    private lateinit var binding: FragmentNewsDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding=DataBindingUtil.inflate(inflater, R.layout.fragment_news_detail, container, false)

        val article=NewsDetailFragmentArgs.fromBundle(requireArguments()).selectedArticle

        val _viewModel: NewsDetailViewModel by viewModels {
            NewsDetailViewModelFactory(article, requireNotNull(activity?.application))
        }

        // Allows Data Binding to Observe LiveData with the lifecycle of this Fragment
        binding.lifecycleOwner=this
        binding.newsDetailViewModel=_viewModel

        binding.backImageView.setOnClickListener {
//            it.findNavController().navigate(R.id.action_newsDetailFragment_to_newsFragment)

            // Navigate up
            requireActivity().onBackPressed()
        }

        return binding.root
    }


}