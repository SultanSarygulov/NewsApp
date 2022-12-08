package com.example.newsapplication.presentation

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isGone
import androidx.core.view.isVisible
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.newsapplication.data.api.Article
import com.example.newsapplication.databinding.ArticleItemBinding
import com.example.newsapplication.databinding.FragmentSavedBinding

class SavedNewsAdapter(val listeners: Listeners): ListAdapter<Article, SavedNewsAdapter.SavedNewsHolder>(DiffCallback()) {

    lateinit var binding: ArticleItemBinding

    var savedNewsList = mutableListOf<Article>()
    fun updateData(currentArticle: Article){
        savedNewsList.add(currentArticle)
        this.submitList(savedNewsList)
        Log.d("TAG", "${savedNewsList.size}")
    }

    inner class SavedNewsHolder(item: View): RecyclerView.ViewHolder(item) {
        val binding = ArticleItemBinding.bind(item)

        fun bind(article: Article) = with(binding) {
            articleTitle.text = article.title
            articleAuthor.text = article.author
            articleDate.text = article.publishedAt
            articleContent.text = article.content
            articleImageProgress.isVisible = true
            Glide
                .with(root)
                .load(article.urlToImage)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
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

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SavedNewsHolder {
        binding = ArticleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SavedNewsHolder(binding.root)
    }

    override fun onBindViewHolder(holder: SavedNewsHolder, position: Int) {
        holder.bind(getItem(position))
    }

//    override fun getItemCount(): Int = savedNewsList.size

}
