package com.dicoding.picodiploma.besti.view.signup

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.trimmedLength
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.dicoding.picodiploma.besti.databinding.ActivitySignUpBinding
import com.dicoding.picodiploma.besti.dataclass.RegisterResponse
import com.dicoding.picodiploma.besti.view.login.LoginActivity

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding
    private lateinit var signUpViewModel: SignUpViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionbar = supportActionBar
        actionbar!!.hide()

        signUpViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(SignUpViewModel::class.java)

        signUpViewModel.getRegisterResponse().observe(this, Observer<RegisterResponse> {
            if (it != null) {
                Toast.makeText(applicationContext, it.status, Toast.LENGTH_LONG).show()
                startActivity(Intent(this, LoginActivity::class.java))
            }
            if (it == null) {
                Toast.makeText(applicationContext, "Failed to create User", Toast.LENGTH_LONG)
                    .show()
            }
        })

        binding.signupButton.setOnClickListener {
            val name = binding.nameEditText.text.toString()
            val profession = binding.professionEditText.text.toString()
            val gender = binding.genderEditText.text.toString()
            val phone = binding.phoneEditText.text.toString()
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            when {
                name.isEmpty() -> {
                    binding.nameEditTextLayout.error = "Masukkan email"
                }
                profession.isEmpty() -> {
                    binding.professionEditTextLayout.error = "Masukkan email"
                }
                gender.isEmpty() -> {
                    binding.genderEditTextLayout.error = "Masukkan email"
                }
                phone.isEmpty() -> {
                    binding.phoneEditTextLayout.error = "Masukkan email"
                }
                email.isEmpty() -> {
                    binding.emailEditTextLayout.error = "Masukkan email"
                }
                !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                    binding.emailEditTextLayout.error = "Email Tidak Valid"
                }
                password.isEmpty() -> {
                    binding.passwordEditTextLayout.error = "Masukkan password"
                }
                password.trimmedLength() < 6 -> {
                    binding.passwordEditTextLayout.error = "Password harus lebih dari 6 karakter"
                }
                else -> {
                    signUpViewModel.register(name, profession, gender, phone, email, password)
                }
            }
        }
        playAnimation()
    }

    private fun playAnimation() {
        ObjectAnimator.ofFloat(binding.imageView, View.TRANSLATION_X, -30f, 30f).apply {
            duration = 6000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()
        val nameTextView =
            ObjectAnimator.ofFloat(binding.nameTextView, View.ALPHA, 1f).setDuration(200)
        val nameEditTextLayout =
            ObjectAnimator.ofFloat(binding.nameEditTextLayout, View.ALPHA, 1f).setDuration(200)
        val professionTextView =
            ObjectAnimator.ofFloat(binding.professionTextView, View.ALPHA, 1f).setDuration(200)
        val professionEditTextLayout =
            ObjectAnimator.ofFloat(binding.professionEditTextLayout, View.ALPHA, 1f)
                .setDuration(200)
        val genderTextView =
            ObjectAnimator.ofFloat(binding.genderTextView, View.ALPHA, 1f).setDuration(200)
        val genderEditTextLayout =
            ObjectAnimator.ofFloat(binding.genderEditTextLayout, View.ALPHA, 1f).setDuration(200)
        val phoneTextView =
            ObjectAnimator.ofFloat(binding.phoneTextView, View.ALPHA, 1f).setDuration(200)
        val phoneEditTextLayout =
            ObjectAnimator.ofFloat(binding.phoneEditTextLayout, View.ALPHA, 1f).setDuration(200)
        val emailTextView =
            ObjectAnimator.ofFloat(binding.emailTextView, View.ALPHA, 1f).setDuration(200)
        val emailEditTextLayout =
            ObjectAnimator.ofFloat(binding.emailEditTextLayout, View.ALPHA, 1f).setDuration(200)
        val passwordTextView =
            ObjectAnimator.ofFloat(binding.passwordTextView, View.ALPHA, 1f).setDuration(200)
        val passwordEditTextLayout =
            ObjectAnimator.ofFloat(binding.passwordEditTextLayout, View.ALPHA, 1f).setDuration(200)
        val signup = ObjectAnimator.ofFloat(binding.signupButton, View.ALPHA, 1f).setDuration(200)


        AnimatorSet().apply {
            playSequentially(
                nameTextView,
                nameEditTextLayout,
                professionTextView,
                professionEditTextLayout,
                genderTextView,
                genderEditTextLayout,
                phoneTextView,
                phoneEditTextLayout,
                emailTextView,
                emailEditTextLayout,
                passwordTextView,
                passwordEditTextLayout,
                signup
            )
            startDelay = 200
        }.start()
    }
}