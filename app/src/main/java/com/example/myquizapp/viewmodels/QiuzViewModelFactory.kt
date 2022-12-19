package com.example.myquizapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myquizapp.model.Repo

class QiuzViewModelFactory constructor(private val repo: Repo) : ViewModelProvider.Factory{


    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if(modelClass.isAssignableFrom(QiuzViewModel::class.java)){
    return QiuzViewModel(this.repo) as T
        } else {
            throw IllegalArgumentException("View not found")
        }
    }
}