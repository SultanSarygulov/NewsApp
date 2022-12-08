package com.example.newsapplication.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsapplication.R
import com.example.newsapplication.data.api.Article
import com.example.newsapplication.databinding.FragmentHomeBinding
import com.example.newsapplication.databinding.FragmentSavedBinding

class SavedFragment : Fragment(), Listeners {

    private lateinit var binding: FragmentSavedBinding
    private lateinit var adapter: SavedNewsAdapter

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
    }

    override fun readArticle(currentArticle: Article) {
        TODO("Not yet implemented")
    }
}