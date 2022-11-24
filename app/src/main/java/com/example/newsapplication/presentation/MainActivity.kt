package com.example.newsapplication.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.ActionBar
import androidx.core.view.isGone
import androidx.core.view.isVisible
import com.example.newsapplication.R
import com.example.newsapplication.data.api.Article
import com.example.newsapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
//        supportActionBar?.setCustomView(R.layout.toolbar_layout)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)
    }
}