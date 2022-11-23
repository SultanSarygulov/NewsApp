package com.example.newsapplication.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapplication.data.NewsRepository
import com.example.newsapplication.data.api.Article
import com.example.newsapplication.databinding.FragmentMainBinding

class HomeFragment : Fragment(), Listeners {

    companion object{
        const val NO_IMAGE_URL = "https://st4.depositphotos.com/14953852/24787/v/600/depositphotos_247872612-stock-illustration-no-image-available-icon-vector.jpg"
    }

    private lateinit var binding: FragmentMainBinding
    private lateinit var adapter: NewsAdapter
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragmen
        binding = FragmentMainBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerview.layoutManager = LinearLayoutManager(requireContext())
        adapter = NewsAdapter(this)
        binding.recyclerview.adapter = adapter

        val repository = NewsRepository()
        val mainViewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, mainViewModelFactory).get(MainViewModel::class.java)
        viewModel.getArticle()
        viewModel.myResponse.observe(viewLifecycleOwner, Observer {articlesList ->
            articlesList.forEach {
                if (it.author == null) it.author = "No Author"
                if (it.urlToImage == null) it.urlToImage = NO_IMAGE_URL
            }
            adapter.setList(articlesList)
        })
    }

    override fun readArticle(currentArticle: Article) {
        val action = HomeFragmentDirections.actionMainFragmentToArticleFragment(currentArticle)
        findNavController().navigate(action)
    }
}