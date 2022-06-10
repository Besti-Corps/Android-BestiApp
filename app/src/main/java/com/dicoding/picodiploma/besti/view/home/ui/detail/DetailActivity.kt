package com.dicoding.picodiploma.besti.view.home.ui.detail

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.dicoding.picodiploma.besti.R
import com.dicoding.picodiploma.besti.view.home.ui.home.Berita

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setupData()
    }

    private fun setupData() {
        val hero = intent.getParcelableExtra<Berita>("Hero") as Berita
        Glide.with(applicationContext)
            .load(hero.photo)
            .into(findViewById(R.id.profileImageView))
        findViewById<TextView>(R.id.nameTextView).text = hero.name
        findViewById<TextView>(R.id.descTextView).text = hero.description
    }
}