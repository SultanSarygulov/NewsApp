package com.example.newsapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapplication.data.MainViewModel
import com.example.newsapplication.data.MainViewModelFactory
import com.example.newsapplication.data.NewsRepository
import com.example.newsapplication.databinding.ActivityMainBinding
import com.example.newsapplication.databinding.FragmentMainBinding

class MainFragment : Fragment(), Listeners {

    private lateinit var binding: FragmentMainBinding
    private lateinit var adapter: ArticleAdapter
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
        adapter = ArticleAdapter(this)
        binding.recyclerview.adapter = adapter

        val repository = NewsRepository()
        val mainViewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, mainViewModelFactory).get(MainViewModel::class.java)
        viewModel.getArticle()
        viewModel.myResponse.observe(viewLifecycleOwner, Observer {response ->
            Log.d("Article", "${response.status}")
            Log.d("Article", "${response.content}")
            Log.d("Article", "${response.title}")
        })
    }

    override fun readArticle() {
        findNavController().navigate(R.id.action_mainFragment_to_articleFragment)
    }
}