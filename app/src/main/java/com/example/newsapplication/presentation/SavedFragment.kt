package com.example.newsapplication.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapplication.R
import com.example.newsapplication.data.api.Article
import com.example.newsapplication.data.room.SavedArticleViewModel
import com.example.newsapplication.databinding.FragmentHomeBinding
import com.example.newsapplication.databinding.FragmentSavedBinding
import com.example.newsapplication.presentation.main.HomeFragmentDirections

class SavedFragment : Fragment(), Listeners {

    private lateinit var binding: FragmentSavedBinding
    private lateinit var adapter: SavedNewsAdapter
    private lateinit var mSavedArticleViewModel: SavedArticleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSavedBinding.inflate(inflater, container, false)

        (requireActivity() as AppCompatActivity).supportActionBar?.setDisplayShowTitleEnabled(true)
        (requireActivity() as AppCompatActivity).supportActionBar?.title = "Saved news"


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerviewSaved.layoutManager = LinearLayoutManager(requireContext())
        adapter = SavedNewsAdapter(this)
        binding.recyclerviewSaved.adapter = adapter

        mSavedArticleViewModel = ViewModelProvider(this)[SavedArticleViewModel::class.java]
        mSavedArticleViewModel.savedArticles.observe(viewLifecycleOwner){articleList ->
            Log.d("Nigger", "${articleList.size}}")
            adapter.setList(articleList)
        }


    }

    override fun readArticle(currentArticle: Article) {
        val action = SavedFragmentDirections.actionSavedFragmentToArticleFragment(currentArticle)
        findNavController().navigate(action)
    }
}