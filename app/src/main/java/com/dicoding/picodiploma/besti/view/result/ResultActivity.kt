package com.dicoding.picodiploma.besti.view.result

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.dicoding.picodiploma.besti.PreferenceHelper
import com.dicoding.picodiploma.besti.R
import com.dicoding.picodiploma.besti.databinding.ActivityResultBinding
import com.dicoding.picodiploma.besti.dataclass.DataPredict
import com.dicoding.picodiploma.besti.view.home.HomeActivity
import com.dicoding.picodiploma.besti.view.home.ui.home.HomeFragment


class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding
    private lateinit var rvSaran: Saran
    private lateinit var adapter: ListSaranAdapter
    private val list = ArrayList<Saran>()
    private lateinit var preferenceHelper: PreferenceHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preferenceHelper = PreferenceHelper(this)

        //Glide.with(applicationContext)
          //  .load()
          //  .into(findViewById(R.id.imgCategory))
        findViewById<TextView>(R.id.tv_label).text = preferenceHelper.getString(PreferenceHelper.LABEL)
        findViewById<TextView>(R.id.tv_accuracy).text = preferenceHelper.getString(PreferenceHelper.ACCURACY) + "%"
        findViewById<TextView>(R.id.tv_type).text = preferenceHelper.getString(PreferenceHelper.TYPE)


        binding.apply {
            rvSaran.layoutManager = LinearLayoutManager(this@ResultActivity)
            rvSaran.setHasFixedSize(true)
            val listHeroAdapter = ListSaranAdapter(list)
            rvSaran.adapter = listHeroAdapter

            list.addAll(listSaran)
        }

        binding.btnDone.setOnClickListener{
            finish()
        }

    }

    private val listSaran: ArrayList<Saran>
        get(){
                val dataName = resources.getStringArray(R.array.saran_organik)
                val dataDescription = resources.getStringArray(R.array.desc_organik)
                val dataPhoto = resources.obtainTypedArray(R.array.img_organik)
                val listsaran = ArrayList<Saran>()
                for (i in dataName.indices) {
                    val saran = Saran(dataName[i],dataDescription[i], dataPhoto.getResourceId(i, -1))
                    listsaran.add(saran)
                }
                return listsaran
        }
}