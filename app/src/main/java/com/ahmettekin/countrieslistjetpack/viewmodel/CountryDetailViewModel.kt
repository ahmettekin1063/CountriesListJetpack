package com.ahmettekin.countrieslistjetpack.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ahmettekin.countrieslistjetpack.model.Country

class CountryDetailViewModel : ViewModel() {
    val countryLiveData = MutableLiveData<Country>()

    fun getDataFromRoom(){
        val country = Country("Turkey", "Asia", "Ankara", "TRY", "Turkish", "www.ss.com")
        countryLiveData.value = country
    }
}