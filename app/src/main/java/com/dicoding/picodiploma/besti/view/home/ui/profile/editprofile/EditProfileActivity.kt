package com.dicoding.picodiploma.besti.view.home.ui.profile.editprofile

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.trimmedLength
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dicoding.picodiploma.besti.PreferenceHelper
import com.dicoding.picodiploma.besti.PreferenceHelper.Companion.PREF_TOKEN
import com.dicoding.picodiploma.besti.R
import com.dicoding.picodiploma.besti.databinding.ActivityEditProfileBinding
import com.dicoding.picodiploma.besti.dataclass.InfoResponse
import com.dicoding.picodiploma.besti.dataclass.UpdateResponse
import com.dicoding.picodiploma.besti.view.home.HomeActivity

class EditProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditProfileBinding
    private lateinit var editProfilViewModel: EditProfilViewModel
    private lateinit var preferenceHelper: PreferenceHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        editProfilViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(EditProfilViewModel::class.java)

        preferenceHelper = PreferenceHelper(this)
        supportActionBar?.hide()

        editProfilViewModel.setInfo(preferenceHelper.getString(PREF_TOKEN).toString())

        editProfilViewModel.getInfoResponse().observe(this, Observer<InfoResponse> {
            if (it != null) {
                Toast.makeText(applicationContext, it.status, Toast.LENGTH_LONG).show()
                findViewById<TextView>(R.id.nameEditText).text = it.data.name
                findViewById<TextView>(R.id.professionEditText).text = it.data.profession
                findViewById<TextView>(R.id.genderEditText).text = it.data.gender
                findViewById<TextView>(R.id.phoneEditText).text = it.data.phone
                findViewById<TextView>(R.id.emailEditText).text = it.data.email
                findViewById<TextView>(R.id.passwordEditText).text = "*********"
            }
            if (it == null) {
                Toast.makeText(applicationContext, "Failed to create User", Toast.LENGTH_LONG)
                    .show()
            }
        })

        editProfilViewModel.getUpdateResponse().observe(this, Observer<UpdateResponse> {
            if (it != null) {
                Toast.makeText(applicationContext, it.status, Toast.LENGTH_LONG).show()
                startActivity(Intent(this, HomeActivity::class.java))
            }
            if (it == null) {
                Toast.makeText(applicationContext, "Failed to create User", Toast.LENGTH_LONG)
                    .show()
            }
        })

        binding.saveButton.setOnClickListener {
            val name = binding.nameEditText.text.toString()
            val profession = binding.professionEditText.text.toString()
            val gender = binding.genderEditText.text.toString()
            val phone = binding.phoneEditText.text.toString()
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            when {
                name.isEmpty() -> {
                    binding.nameEditTextLayout.error = "Masukkan nama"
                }
                editProfilViewModel.infoUser.value!!.data.name == name -> {
                    binding.nameEditTextLayout.error = "Tidak ada perubahan nama"
                }
                profession.isEmpty() -> {
                    binding.professionEditTextLayout.error = "Masukkan profesi"
                }
                editProfilViewModel.infoUser.value!!.data.profession == profession -> {
                    binding.professionEditTextLayout.error = "Tidak ada perubahan profesi"
                }
                gender.isEmpty() -> {
                    binding.genderEditTextLayout.error = "Masukkan gender"
                }
                editProfilViewModel.infoUser.value!!.data.gender == gender -> {
                    binding.genderEditTextLayout.error = "Tidak ada perubahan jenis kelamin"
                }
                phone.isEmpty() -> {
                    binding.phoneEditTextLayout.error = "Masukkan phone"
                }
                editProfilViewModel.infoUser.value!!.data.phone == phone -> {
                    binding.phoneEditTextLayout.error = "Tidak ada perubahan phone"
                }
                email.isEmpty() -> {
                    binding.emailEditTextLayout.error = "Masukkan email"
                }
                !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                    binding.emailEditTextLayout.error = "Email Tidak Valid"
                }
                editProfilViewModel.infoUser.value!!.data.email == email -> {
                    binding.emailEditTextLayout.error = "Tidak ada perubahan email"
                }
                password.isEmpty() -> {
                    binding.passwordEditTextLayout.error = "Masukkan password"
                }
                password.trimmedLength() < 6 -> {
                    binding.passwordEditTextLayout.error = "Password harus lebih dari 6 karakter"
                }
                else -> {
                    editProfilViewModel.setUpdate(
                        preferenceHelper.getString(PREF_TOKEN).toString(),
                        name,
                        profession,
                        gender,
                        phone,
                        email,
                        password
                    )
                }
            }
        }
    }

    companion object {
        const val NAME = "Name"
    }
}