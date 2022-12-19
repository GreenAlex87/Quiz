package com.example.myquizapp.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.myquizapp.MyApplication
import com.example.myquizapp.R
import com.example.myquizapp.viewmodels.QiuzViewModel
import com.example.myquizapp.viewmodels.QiuzViewModelFactory

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: QiuzViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


         viewModel =
            ViewModelProvider(this, QiuzViewModelFactory((application as MyApplication).repo)).get(
                QiuzViewModel::class.java
            )

        setContentView(R.layout.activity_main)
        supportActionBar?.hide()


    }
}