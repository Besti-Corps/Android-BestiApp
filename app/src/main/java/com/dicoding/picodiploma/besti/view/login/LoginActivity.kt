package com.dicoding.picodiploma.besti.view.login

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
import com.dicoding.picodiploma.besti.PreferenceHelper
import com.dicoding.picodiploma.besti.PreferenceHelper.Companion.PREF_TOKEN
import com.dicoding.picodiploma.besti.PreferenceHelper.Companion.STATE_KEY
import com.dicoding.picodiploma.besti.databinding.ActivityLoginBinding
import com.dicoding.picodiploma.besti.dataclass.LoginResponse
import com.dicoding.picodiploma.besti.view.home.HomeActivity
import com.dicoding.picodiploma.besti.view.signup.SignUpActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var binding: ActivityLoginBinding
    private lateinit var preferenceHelper: PreferenceHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionbar = supportActionBar
        actionbar!!.hide()

        binding.daftardulu.setOnClickListener {
            startActivity(Intent(this, SignUpActivity::class.java))
        }

        preferenceHelper = PreferenceHelper(this)

        loginViewModel = ViewModelProvider(
            this,
            ViewModelProvider.NewInstanceFactory()
        ).get(LoginViewModel::class.java)

        loginViewModel.getLoginResponse().observe(this, Observer<LoginResponse> {
            if (it != null) {
//                Toast.makeText(
//                    applicationContext,
//                    "kamu telah login",
//                    Toast.LENGTH_LONG
//                ).show()
                val tokenUser = (it.data.token)

                preferenceHelper.put(PREF_TOKEN, tokenUser)
                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            }
            if (it == null) {
//                Toast.makeText(applicationContext, "Password atau Email Salah", Toast.LENGTH_LONG)
//                    .show()
            }
        })

        binding.loginButton.setOnClickListener {
            val email = binding.emailEditText.text.toString()
            val password = binding.passwordEditText.text.toString()
            when {
                email.isEmpty() -> {
                    binding.emailEditTextLayout.error = "Masukkan email"
                }
                password.isEmpty() -> {
                    binding.passwordEditTextLayout.error = "Masukkan password"
                }
                !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                    binding.emailEditTextLayout.error = "Email Salah"
                }
                password.trimmedLength() < 6 -> {
                    binding.passwordEditTextLayout.error = "Password kurang"
                }

                else -> {
                    loginViewModel.login(email, password)
                    preferenceHelper.put(STATE_KEY, true)
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
        val daftar =
            ObjectAnimator.ofFloat(binding.daftardulu, View.ALPHA, 1f).setDuration(200)
        val daftar1 =
            ObjectAnimator.ofFloat(binding.daftardulu1, View.ALPHA, 1f).setDuration(200)
        val emailTextView =
            ObjectAnimator.ofFloat(binding.emailTextView, View.ALPHA, 1f).setDuration(200)
        val emailEditTextLayout =
            ObjectAnimator.ofFloat(binding.emailEditTextLayout, View.ALPHA, 1f).setDuration(200)
        val passwordTextView =
            ObjectAnimator.ofFloat(binding.passwordTextView, View.ALPHA, 1f).setDuration(200)
        val passwordEditTextLayout =
            ObjectAnimator.ofFloat(binding.passwordEditTextLayout, View.ALPHA, 1f).setDuration(200)
        val login = ObjectAnimator.ofFloat(binding.loginButton, View.ALPHA, 1f).setDuration(200)

        AnimatorSet().apply {
            playSequentially(
                daftar,
                daftar1,
                emailTextView,
                emailEditTextLayout,
                passwordTextView,
                passwordEditTextLayout,
                login
            )
            startDelay = 100
        }.start()
    }

}