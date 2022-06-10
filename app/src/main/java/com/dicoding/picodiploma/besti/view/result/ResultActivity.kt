package com.dicoding.picodiploma.besti.view.result

import android.annotation.SuppressLint
import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.dicoding.picodiploma.besti.PreferenceHelper
import com.dicoding.picodiploma.besti.R
import com.dicoding.picodiploma.besti.databinding.ActivityResultBinding
import com.dicoding.picodiploma.besti.rotateBitmap


class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding
    private lateinit var rvSaran: Saran
    private lateinit var adapter: ListSaranAdapter
    private val list = ArrayList<Saran>()
    private lateinit var preferenceHelper: PreferenceHelper

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        preferenceHelper = PreferenceHelper(this)

        val image = intent.getStringExtra("BITMAP")
        val result = rotateBitmap(
            BitmapFactory.decodeFile(image), true
        )
        Glide.with(applicationContext)
            .load(result)
            .into(binding.imgCategory)
        binding.tvLabel.text =
            preferenceHelper.getString(PreferenceHelper.LABEL)
        binding.tvAccuracy.text =
            preferenceHelper.getString(PreferenceHelper.ACCURACY) + "%"
        binding.tvType.text =
            preferenceHelper.getString(PreferenceHelper.TYPE)


        binding.apply {
            rvSaran.layoutManager = LinearLayoutManager(this@ResultActivity)
            rvSaran.setHasFixedSize(true)
            val listHeroAdapter = ListSaranAdapter(list)
            rvSaran.adapter = listHeroAdapter

            if (preferenceHelper.getString(PreferenceHelper.TYPE) == "Organik") {
                list.addAll(organik)
            } else if (preferenceHelper.getString(PreferenceHelper.TYPE) == "Anorganik") {
                list.addAll(anorganik)
            } else if (preferenceHelper.getString(PreferenceHelper.TYPE) == "Kertas") {
                list.addAll(kertas)
            } else if (preferenceHelper.getString(PreferenceHelper.TYPE) == "B3") {
                list.addAll(b3)
            } else if (preferenceHelper.getString(PreferenceHelper.TYPE) == "Residu") {
                list.addAll(residu)
            }
        }

        binding.btnDone.setOnClickListener {
            finish()
        }

    }

    private val organik: ArrayList<Saran>
        get() {
            val dataName = resources.getStringArray(R.array.saran_organik)
            val dataDescription = resources.getStringArray(R.array.desc_organik)
            val dataPhoto = resources.obtainTypedArray(R.array.img_organik)
            val listsaran = ArrayList<Saran>()
            for (i in dataName.indices) {
                val saran = Saran(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1))
                listsaran.add(saran)
            }
            return listsaran
        }
    private val anorganik: ArrayList<Saran>
        get() {
            val dataName = resources.getStringArray(R.array.saran_anorganik)
            val dataDescription = resources.getStringArray(R.array.desc_anorganik)
            val dataPhoto = resources.obtainTypedArray(R.array.img_anorganik)
            val listsaran = ArrayList<Saran>()
            for (i in dataName.indices) {
                val saran = Saran(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1))
                listsaran.add(saran)
            }
            return listsaran
        }
    private val kertas: ArrayList<Saran>
        get() {
            val dataName = resources.getStringArray(R.array.saran_kertas)
            val dataDescription = resources.getStringArray(R.array.desc_kertas)
            val dataPhoto = resources.obtainTypedArray(R.array.img_kertas)
            val listsaran = ArrayList<Saran>()
            for (i in dataName.indices) {
                val saran = Saran(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1))
                listsaran.add(saran)
            }
            return listsaran
        }

    private val b3: ArrayList<Saran>
        get() {
            val dataName = resources.getStringArray(R.array.saran_B3)
            val dataDescription = resources.getStringArray(R.array.desc_B3)
            val dataPhoto = resources.obtainTypedArray(R.array.desc_B3)
            val listsaran = ArrayList<Saran>()
            for (i in dataName.indices) {
                val saran = Saran(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1))
                listsaran.add(saran)
            }
            return listsaran
        }

    private val residu: ArrayList<Saran>
        get() {
            val dataName = resources.getStringArray(R.array.saran_residu)
            val dataDescription = resources.getStringArray(R.array.desc_residu)
            val dataPhoto = resources.obtainTypedArray(R.array.img_residu)
            val listsaran = ArrayList<Saran>()
            for (i in dataName.indices) {
                val saran = Saran(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1))
                listsaran.add(saran)
            }
            return listsaran
        }


}