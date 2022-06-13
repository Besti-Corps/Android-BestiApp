package com.dicoding.picodiploma.besti.view.home.ui.home

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.picodiploma.besti.PreferenceHelper
import com.dicoding.picodiploma.besti.R
import com.dicoding.picodiploma.besti.databinding.FragmentHomeBinding
import com.dicoding.picodiploma.besti.dataclass.InfoResponse

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val homeViewModel: HomeViewModel by viewModels()
    private lateinit var preferenceHelper: PreferenceHelper

    private lateinit var rvHeroes: RecyclerView
    private val list = ArrayList<Berita>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        preferenceHelper = PreferenceHelper(requireContext())

        homeViewModel.setInfo(preferenceHelper.getString(PreferenceHelper.PREF_TOKEN).toString())

        homeViewModel.getInfoResponse().observe(viewLifecycleOwner, Observer<InfoResponse> {
            if (it != null) {
//                Toast.makeText(context, it.status, Toast.LENGTH_SHORT).show()
                val tvName: TextView = requireActivity().findViewById(R.id.tv_username)
                val tvPhone: TextView = requireActivity().findViewById(R.id.tv_description)
                tvName.text = "Nama : " + it.data.name
                tvPhone.text = "Phone : " + it.data.phone
            }
            if (it == null) {
//                Toast.makeText(context, "Failed to create User", Toast.LENGTH_SHORT).show()
            }
        })

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        rvHeroes = view.findViewById(R.id.rv_heroes)
        rvHeroes.setHasFixedSize(true)

        list.addAll(listHeroes)
        showRecyclerList()
    }

    private val listHeroes: ArrayList<Berita>
        get() {
            val dataName = resources.getStringArray(R.array.data_judul_berita)
            val dataDescription = resources.getStringArray(R.array.data_description)
            val dataPhotoVector = resources.obtainTypedArray(R.array.data_photo_vector)
            val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
            val listHero = ArrayList<Berita>()
            for (i in dataName.indices) {
                val berita = Berita(
                    dataName[i],
                    dataDescription[i],
                    dataPhotoVector.getResourceId(i, -1),
                    dataPhoto.getResourceId(i, -1)
                )
                listHero.add(berita)
            }
            return listHero
        }

    private fun showRecyclerList() {
        if (requireActivity().resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            rvHeroes.layoutManager = GridLayoutManager(activity, 2)
        } else {
            rvHeroes.layoutManager = LinearLayoutManager(activity)
        }
        val listHeroAdapter = ListBeritaAdapter(list)
        rvHeroes.adapter = listHeroAdapter

        listHeroAdapter.setOnItemClickCallback(object : ListBeritaAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Berita) {
                showSelectedHero(data)
            }
        })
    }

    private fun showSelectedHero(berita: Berita) {
//        Toast.makeText(requireActivity(), "Kamu memilih " + berita.name, Toast.LENGTH_SHORT).show()

    }
}