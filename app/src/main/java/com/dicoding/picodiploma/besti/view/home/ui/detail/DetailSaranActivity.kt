package com.dicoding.picodiploma.besti.view.home.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.bumptech.glide.Glide
import com.dicoding.picodiploma.besti.R
import com.dicoding.picodiploma.besti.databinding.ActivityDetailSaranBinding
import com.dicoding.picodiploma.besti.view.result.Saran

class DetailSaranActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_saran)

        initAction()
    }

    private fun initAction() {
        val saran = intent.getParcelableExtra<Saran>("Saran") as Saran
        Glide.with(applicationContext)
            .load(saran.photo)
            .into(findViewById(R.id.img_saran))
        findViewById<TextView>(R.id.tv_name_saran).text = saran.name
        findViewById<TextView>(R.id.tvDescSaran).text = saran.description
    }
}