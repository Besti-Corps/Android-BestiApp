package com.dicoding.picodiploma.besti.view.home.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.dicoding.picodiploma.besti.PreferenceHelper
import com.dicoding.picodiploma.besti.databinding.FragmentProfileBinding
import com.dicoding.picodiploma.besti.view.camera.CameraActivity
import com.dicoding.picodiploma.besti.view.home.ui.profile.editprofile.EditProfileActivity
import com.dicoding.picodiploma.besti.view.login.LoginActivity

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private lateinit var preferenceHelper: PreferenceHelper

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(ProfileViewModel::class.java)

        preferenceHelper = PreferenceHelper(requireContext())

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val root: View = binding.root

        _binding!!.button2.setOnClickListener{
            val intent = Intent(activity, EditProfileActivity::class.java)
            activity?.startActivity(intent)
        }

        _binding!!.button3.setOnClickListener{
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