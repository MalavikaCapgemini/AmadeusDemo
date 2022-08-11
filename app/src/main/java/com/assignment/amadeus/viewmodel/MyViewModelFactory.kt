package com.assignment.amadeus.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.assignment.amadeus.data.local.CityDatabase

class MyViewModelFactory constructor(private val noteDatabase: CityDatabase): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            MainViewModel(noteDatabase) as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}