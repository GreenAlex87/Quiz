package com.example.myquizapp

import android.app.Application
import com.example.myquizapp.model.Repo
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class MyApplication : Application() {
    override fun onCreate() {
        super.onCreate()
         }
}