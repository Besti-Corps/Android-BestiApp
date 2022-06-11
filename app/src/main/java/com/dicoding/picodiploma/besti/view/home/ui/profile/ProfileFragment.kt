package com.dicoding.picodiploma.besti.view.home.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dicoding.picodiploma.besti.PreferenceHelper
import com.dicoding.picodiploma.besti.R
import com.dicoding.picodiploma.besti.databinding.FragmentHomeBinding
import com.dicoding.picodiploma.besti.databinding.FragmentProfileBinding
import com.dicoding.picodiploma.besti.dataclass.InfoResponse
import com.dicoding.picodiploma.besti.view.home.ui.home.HomeViewModel
import com.dicoding.picodiploma.besti.view.home.ui.profile.editprofile.EditProfileActivity
import com.dicoding.picodiploma.besti.view.home.ui.profile.review.ReviewActivity
import com.dicoding.picodiploma.besti.view.login.LoginActivity
import com.dicoding.picodiploma.besti.view.setting.SettingActivity

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private lateinit var preferenceHelper: PreferenceHelper
    private val binding get() = _binding!!
    private val profileViewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        preferenceHelper = PreferenceHelper(requireContext())

        profileViewModel.setInfo(preferenceHelper.getString(PreferenceHelper.PREF_TOKEN).toString())

        profileViewModel.getInfoResponse().observe(viewLifecycleOwner, Observer<InfoResponse> {
            if (it != null) {
                Toast.makeText(context, it.status, Toast.LENGTH_LONG).show()
                val tvName: TextView = requireActivity().findViewById(R.id.username)
                val tvPhone: TextView = requireActivity().findViewById(R.id.phone)
                val tvEmail: TextView = requireActivity().findViewById(R.id.email)
                tvName.text =it.data.name
                tvPhone.text = it.data.phone
                tvEmail.text = it.data.email
            }
            if (it == null) {
                Toast.makeText(context, "Failed to create User", Toast.LENGTH_LONG).show()
            }
        })

        _binding!!.button2.setOnClickListener {
            val intent = Intent(activity, EditProfileActivity::class.java)
            activity?.startActivity(intent)
        }

        _binding!!.button5.setOnClickListener{
            val intent = Intent(activity, ReviewActivity::class.java)
            activity?.startActivity(intent)
        }

        _binding!!.button.setOnClickListener {
            val j = Intent(activity, SettingActivity::class.java)
            activity?.startActivity(j)
        }

        _binding!!.button3.setOnClickListener {
            preferenceHelper.clear()
            val i = Intent(activity, LoginActivity::class.java)
            activity?.startActivity(i)
            activity?.finish()
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}