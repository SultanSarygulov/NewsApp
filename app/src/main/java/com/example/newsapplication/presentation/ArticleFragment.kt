package com.example.newsapplication.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.newsapplication.R
import com.example.newsapplication.databinding.FragmentArticleBinding

class ArticleFragment : Fragment() {

    private lateinit var binding: FragmentArticleBinding
    private val args by navArgs<ArticleFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentArticleBinding.inflate(inflater, container, false)

        binding.apply {
            articleTitleRead.setText(args.currentArticle.title)
            articleDescriptionRead.setText(args.currentArticle.description)
            articleContentRead.setText(args.currentArticle.content)
            Glide
                .with(root)
                .load(args.currentArticle.urlToImage)
                .into(articleImageRead)
        }

        return binding.root
    }

}