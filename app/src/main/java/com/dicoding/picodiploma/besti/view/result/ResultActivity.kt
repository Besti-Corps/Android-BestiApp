package com.dicoding.picodiploma.besti.view.result

import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.picodiploma.besti.R
import com.dicoding.picodiploma.besti.databinding.ActivityResultBinding
import com.dicoding.picodiploma.besti.dataclass.DataPredict


class ResultActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_IMAGE = "0"
        const val EXTRA_LABEL = "extra_label"
        const val EXTRA_ACCURACY = "extra_accuracy"
        const val EXTRA_TYPE = "extra_type"
    }

    private lateinit var binding: ActivityResultBinding
    private lateinit var rvSaran: Saran
    private lateinit var adapter: ListSaranAdapter
    private val list = ArrayList<Saran>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.apply {
            rvSaran.layoutManager = LinearLayoutManager(this@ResultActivity)
            rvSaran.setHasFixedSize(true)
            val listHeroAdapter = ListSaranAdapter(list)
            rvSaran.adapter = listHeroAdapter
            list.addAll(listSaran)

            val bundle = intent.extras
            val data: DataPredict? = bundle?.getParcelable<DataPredict>("EXTRA_DATA")!! as DataPredict
            tvLabel.text = data?.label
            tvAccuracy.text = data?.accuracy.toString()
        }

        binding.btnDone.setOnClickListener{


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