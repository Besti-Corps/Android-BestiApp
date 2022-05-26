package com.dicoding.picodiploma.besti.view.home.ui.home

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.picodiploma.besti.R
import com.dicoding.picodiploma.besti.databinding.FragmentHomeBinding
import java.util.ArrayList

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var rvHeroes: RecyclerView
    private val list = ArrayList<Berita>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        //val homeViewModel =
        //    ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

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
            val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
            val listHero = ArrayList<Berita>()
            for (i in dataName.indices) {
                val hero = Berita(dataName[i],dataDescription[i], dataPhoto.getResourceId(i, -1))
                listHero.add(hero)
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

    private fun showSelectedHero(hero: Berita) {
        Toast.makeText(requireActivity(), "Kamu memilih " + hero.name, Toast.LENGTH_SHORT).show()
    }
}