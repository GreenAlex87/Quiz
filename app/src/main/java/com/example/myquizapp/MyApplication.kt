package com.example.myquizapp

import android.app.Application
import com.example.myquizapp.model.Repo

class MyApplication : Application() {

    lateinit var repo: Repo

    override fun onCreate() {
        super.onCreate()

        repo = Repo()
    }
}