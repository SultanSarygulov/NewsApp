package com.example.newsapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapplication.databinding.ArticleItemBinding

class ArticleAdapter(val listeners: Listeners): RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {

    lateinit var binding: ArticleItemBinding

    var contentList = listOf(1,2,3,4,5,6,7,8,9,10)

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleAdapter.ViewHolder {
        binding = ArticleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ArticleAdapter.ViewHolder, position: Int) {
        binding.articleContent.text = contentList[position].toString()
        binding.articleCard.setOnClickListener {
            listeners.readArticle()
        }
    }

    override fun getItemCount(): Int = contentList.size
}