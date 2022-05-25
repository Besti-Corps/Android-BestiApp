package com.dicoding.picodiploma.besti

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper

class SplashScreen : AppCompatActivity() {

    private lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)


        handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            val intentSplashScreen = Intent(this, IntroActivity::class.java)
            startActivity(intentSplashScreen)
            finish()
        }, 1000)

    }
}