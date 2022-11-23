package com.example.newsapplication.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapplication.data.api.Article
import com.example.newsapplication.data.api.ArticlesArray
import com.example.newsapplication.databinding.ArticleItemBinding

class ArticleAdapter(val listeners: Listeners): RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {

    lateinit var binding: ArticleItemBinding

    var contentList = listOf<Article>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleAdapter.ViewHolder {
        binding = ArticleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val currentArticle = contentList[position]

        binding.articleContent.text = currentArticle.content
        binding.articleCard.setOnClickListener {
            listeners.readArticle()
        }
    }

    inner class ViewHolder: RecyclerView.ViewHolder(binding.root)

    override fun getItemCount(): Int = contentList.size
}