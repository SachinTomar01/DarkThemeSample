package com.example.sample

import android.content.SharedPreferences
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.NightMode
import com.example.sample.application.MyApplication
import com.example.sample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var theme: Int = AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
    @NightMode
    private var mode: Int= AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val sp : SharedPreferences = MyApplication.applicationContext().getSharedPreferences("MySharedPref", MODE_PRIVATE)
        theme = sp.getInt("theme", AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        mode = AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
        if (theme == AppCompatDelegate.MODE_NIGHT_YES) {
            mode = AppCompatDelegate.MODE_NIGHT_YES
        } else if (theme == AppCompatDelegate.MODE_NIGHT_NO) {
            mode = AppCompatDelegate.MODE_NIGHT_NO
        }
        AppCompatDelegate.setDefaultNightMode(mode)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initWidgets()
    }

    private fun initWidgets() {
        when (theme) {
            AppCompatDelegate.MODE_NIGHT_YES -> {
                binding.night.isChecked = true
            }
            AppCompatDelegate.MODE_NIGHT_NO -> {
                binding.day.isChecked = true
            }
            else -> {
                binding.auto.isChecked = true
            }
        }

        setThemeText()

        binding.submitButton.setOnClickListener {
            switchTheme()
        }
    }

    private fun setThemeText() {

        if (AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM ||
            AppCompatDelegate.getDefaultNightMode() == AppCompatDelegate.MODE_NIGHT_UNSPECIFIED
        ) {
            binding.themeHeader.setText(R.string.select_theme_auto)
        } else {

            binding.themeHeader.setText(R.string.select_theme)
        }
    }

    private fun switchTheme() {
        val editor =  MyApplication.sharedPreferences.edit()
        @NightMode
        mode = AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
        editor.putInt("theme", AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        editor.apply()
        when (binding.themeLayout.checkedRadioButtonId) {

            binding.night.id -> {
                mode = AppCompatDelegate.MODE_NIGHT_YES
                editor.putInt("theme", AppCompatDelegate.MODE_NIGHT_YES)
                editor.apply()
            }

            binding.day.id -> {
                mode = AppCompatDelegate.MODE_NIGHT_NO
                editor.putInt("theme", AppCompatDelegate.MODE_NIGHT_NO)
                editor.apply()
            }
        }

        AppCompatDelegate.setDefaultNightMode(mode)

        setThemeText()
    }
}