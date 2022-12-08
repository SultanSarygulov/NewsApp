package com.example.newsapplication.presentation

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.newsapplication.R
import com.example.newsapplication.data.api.Article
import com.example.newsapplication.databinding.FragmentArticleBinding
import com.example.newsapplication.presentation.main.NewsAdapter

class ArticleFragment : Fragment(), Listeners {

    private lateinit var binding: FragmentArticleBinding
    private lateinit var adapter: SavedNewsAdapter
    private val args by navArgs<ArticleFragmentArgs>()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentArticleBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.webview.webViewClient = WebViewClient()
        binding.webview.loadUrl(args.currentArticle.url)

        adapter = SavedNewsAdapter(this)

        binding.favouriteButton.setOnClickListener {
            adapter.updateData(args.currentArticle)
            Toast.makeText(requireContext(), "Article saved", Toast.LENGTH_LONG).show()
        }
    }

    inner class WebViewClient: android.webkit.WebViewClient(){

        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return false
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            binding.progressBar.isVisible = false
            binding.webview.isVisible = true
        }
    }

    override fun readArticle(currentArticle: Article) {
        TODO("Not yet implemented")
    }
}