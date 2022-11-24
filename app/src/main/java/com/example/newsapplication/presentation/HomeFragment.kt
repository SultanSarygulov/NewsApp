package com.example.newsapplication.presentation

import android.icu.lang.UCharacter.GraphemeClusterBreak.L
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapplication.R
import com.example.newsapplication.data.NewsRepository
import com.example.newsapplication.data.api.Article
import com.example.newsapplication.databinding.FragmentHomeBinding

class HomeFragment : Fragment(), Listeners {

    companion object{
        const val NO_IMAGE_URL = "https://st4.depositphotos.com/14953852/24787/v/600/depositphotos_247872612-stock-illustration-no-image-available-icon-vector.jpg"
    }

    private lateinit var binding: FragmentHomeBinding
    private lateinit var adapter: NewsAdapter
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragmen
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapter()
        setViewModel()
        getNews()


    }

    private fun setAdapter(){
        binding.recyclerview.layoutManager = LinearLayoutManager(requireContext())
        adapter = NewsAdapter(this)
        binding.recyclerview.adapter = adapter

    }

    private fun setViewModel(){
        val repository = NewsRepository()
        val mainViewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, mainViewModelFactory).get(MainViewModel::class.java)
    }

    private fun getNews(){
        viewModel.getArticle()
        viewModel.myResponse.observe(viewLifecycleOwner, Observer {response ->

            response.body()?.let { response ->
                response.articles.forEach { article ->
                    if (article.urlToImage == null ) article.urlToImage = NO_IMAGE_URL
                    if (article.title == null ) article.title = "No Title"
                    if (article.author == null ) article.urlToImage = "No Author"
                }
                adapter.setList(response.articles)
            } ?:
            Toast.makeText(requireContext(), "Something went wrong(", Toast.LENGTH_SHORT)
        })
    }

    override fun readArticle(currentArticle: Article) {
        val action = HomeFragmentDirections.actionMainFragmentToArticleFragment(currentArticle)
        findNavController().navigate(action)

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater): Boolean {

        inflater.inflate(R.menu.search, menu)
        return true
    }



}