package com.dicoding.picodiploma.besti.view.setting

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.picodiploma.besti.databinding.ActivitySettingBinding
import com.dicoding.picodiploma.besti.view.setting.theme.ThemeActivity

class SettingActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySettingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btnDone.setOnClickListener {
            startActivity(Intent(this, ThemeActivity::class.java))
        }

        binding.btnDone2.setOnClickListener {
            startActivity(Intent(Settings.ACTION_LOCALE_SETTINGS))
        }
    }
}