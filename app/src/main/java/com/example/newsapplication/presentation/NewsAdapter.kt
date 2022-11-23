package com.example.newsapplication.presentation

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newsapplication.data.api.Article
import com.example.newsapplication.databinding.ArticleItemBinding

class NewsAdapter(val listeners: Listeners): RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    lateinit var binding: ArticleItemBinding

    var articleList = mutableListOf<Article>()
    fun setList(newList: List<Article>){
        val callback = NewsUtilCallback(articleList, newList)
        val diffResult = DiffUtil.calculateDiff(callback)
        articleList.clear()
        articleList.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }

    inner class ViewHolder(item: View): RecyclerView.ViewHolder(item){
        val binding = ArticleItemBinding.bind(item)
        fun bind(article: Article) = with(binding){
            articleTitle.text = article.title
            articleAuthor.text = article.author ?: "No Author"
            articleDate.text = article.publishedAt
            articleContent.text = article.content
            Glide
                .with(root)
                .load(article.urlToImage)
                .into(articleImage)

            articleCard.setOnClickListener {
                listeners.readArticle(article)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsAdapter.ViewHolder {
        binding = ArticleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(articleList[position])
        Log.d("TAG", "${articleList[position].author}")
    }



    override fun getItemCount(): Int = articleList.size
}