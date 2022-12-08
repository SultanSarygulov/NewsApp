package com.example.newsapplication.presentation.main

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.core.view.isGone
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapplication.R
import com.example.newsapplication.data.NewsRepository
import com.example.newsapplication.data.api.Article
import com.example.newsapplication.databinding.FragmentHomeBinding
import com.example.newsapplication.presentation.Listeners
import com.example.newsapplication.presentation.MainViewModelFactory

class HomeFragment : Fragment(), Listeners, SearchView.OnQueryTextListener {

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

        activity?.setActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(true)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Breaking news"
        binding.searchView.setOnQueryTextListener(this)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapter()
        setViewModel()
        getNews()

        binding.refresh.setOnClickListener {
            viewModel.myResponse.observe(viewLifecycleOwner) { articles ->
                adapter.setList(articles)
            }
        }

        binding.saved.setOnClickListener {
            val action = HomeFragmentDirections.actionMainFragmentToSavedFragment()
            findNavController().navigate(action)
        }
    }

    private fun setAdapter(){
        binding.recyclerview.layoutManager = LinearLayoutManager(requireContext())
        adapter = NewsAdapter(this)
        binding.recyclerview.adapter = adapter

    }

    private fun setViewModel(){
        val repository = NewsRepository()
        val mainViewModelFactory = MainViewModelFactory(repository, activity?.application)
        viewModel = ViewModelProvider(this, mainViewModelFactory).get(MainViewModel::class.java)
    }

    private fun getNews(){
        viewModel.getArticle()
        viewModel.myResponse.observe(viewLifecycleOwner, Observer {articles ->
            binding.progressBar.isGone = true
            articles.forEach { article ->
                if (article.urlToImage == null ) article.urlToImage = NO_IMAGE_URL
                if (article.title == null ) article.title = "No Title"
                if (article.author == null ) article.author = "No Author"
            }
            adapter.setList(articles)

        })
    }

    override fun readArticle(currentArticle: Article) {
        val action = HomeFragmentDirections.actionMainFragmentToArticleFragment(currentArticle)
        findNavController().navigate(action)

    }

    override fun onQueryTextSubmit(query: String?): Boolean {

        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        if (query != null){
            viewModel.myResponse.observe(viewLifecycleOwner) { articles ->
                val newList = articles.filter { query in it.title}
                adapter.setList(newList)
            }
        }
        return true
    }
}