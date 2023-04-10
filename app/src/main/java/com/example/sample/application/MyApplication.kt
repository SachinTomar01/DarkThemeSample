package com.example.sample.application

import android.app.Application
import android.content.Context
import android.content.SharedPreferences

class MyApplication: Application() {

    init {
        instance = this
    }

    companion object {
        private var instance: Application? = null
        lateinit var sharedPreferences: SharedPreferences


        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }

    override fun onCreate() {
        sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE)
        super.onCreate()
    }
}