package com.example.newsapplication.presentation.main

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.newsapplication.data.api.Article
import com.example.newsapplication.databinding.ArticleItemBinding
import com.example.newsapplication.presentation.Listeners
import com.example.newsapplication.presentation.NewsUtilCallback

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
            articleAuthor.text = article.author
            articleDate.text = article.publishedAt
            articleContent.text = article.content
            articleImageProgress.isVisible = true
            Glide
                .with(root)
                .load(article.urlToImage)
                .listener(object : RequestListener<Drawable>{
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        binding.articleImageProgress.isGone = true
                        return false
                    }
                })
                .into(articleImage)

            articleCard.setOnClickListener {
                listeners.readArticle(article)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ArticleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Log.d("Nigger", "${articleList[position].id}")
        holder.bind(articleList[position])
    }

    override fun getItemCount(): Int = articleList.size



}