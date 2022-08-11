package com.assignment.amadeus.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.assignment.amadeus.data.local.CityDatabase
import com.assignment.amadeus.data.local.CityEntity
import kotlinx.coroutines.launch

class MainViewModel(private val cityDatabase: CityDatabase) : ViewModel() {

    val cities = cityDatabase.dao.getAllCity()
    var cityList: LiveData<List<CityEntity>> = MutableLiveData()
    private val _loading = MutableLiveData<Boolean>()
    val loading: MutableLiveData<Boolean>
        get() = _loading

    init {
        _loading.value = true
        searchAllCity()
    }

    fun insertCity(cityEntity: CityEntity) {
        viewModelScope.launch {
            cityDatabase.dao.insertCity(cityEntity)
        }
    }

    fun searchAllCity(): LiveData<List<CityEntity>> {
        cityList = MutableLiveData()
        cityList = cityDatabase.dao.getAllCity()
        _loading.value = false
        return cityList
    }

    fun searchCityBYName(cityName: String): LiveData<List<CityEntity>> {
        _loading.value = true
        cityList = MutableLiveData()
        cityList = cityDatabase.dao.getSearchCities(cityName)
        _loading.value = false
        return cityList
    }


}