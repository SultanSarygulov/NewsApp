package com.example.newsapplication.presentation

import androidx.recyclerview.widget.DiffUtil
import com.example.newsapplication.data.api.Article

class DiffCallback: DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.url == newItem.url
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem == newItem
    }
}