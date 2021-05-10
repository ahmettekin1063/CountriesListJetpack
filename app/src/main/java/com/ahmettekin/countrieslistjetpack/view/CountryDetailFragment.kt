package com.ahmettekin.countrieslistjetpack.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.ahmettekin.countrieslistjetpack.R
import com.ahmettekin.countrieslistjetpack.databinding.FragmentCountryDetailBinding
import com.ahmettekin.countrieslistjetpack.util.downloadFromUrl
import com.ahmettekin.countrieslistjetpack.viewmodel.CountryDetailViewModel
import kotlinx.android.synthetic.main.fragment_country_detail.*

class CountryDetailFragment : Fragment() {
    private lateinit var viewModel: CountryDetailViewModel
    private var countryUuid = 0
    private lateinit var databinding: FragmentCountryDetailBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        databinding = DataBindingUtil.inflate(inflater,R.layout.fragment_country_detail,container,false)
        return databinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            countryUuid = CountryDetailFragmentArgs.fromBundle(it).countryUuid
        }

        viewModel = ViewModelProviders.of(this).get(CountryDetailViewModel::class.java)
        viewModel.getDataFromRoom(countryUuid)

        observeLiveData()
    }

    private fun observeLiveData(){
        viewModel.countryLiveData.observe(viewLifecycleOwner,{ country->
            country?.let {
                databinding.selectedCountry = it
                /*tvDetailCountryName.text = country.countryName
                tvDetailCountryCapital.text = country.countryCapital
                tvDetailCountryCurrency.text = country.countryCurrency
                tvDetailCountryLanguage.text = country.countryLanguage
                tvDetailCountryRegion.text = country.countryRegion
                context?.let {
                    imgDetailCountryImage.downloadFromUrl(country.imageUrl)
                }*/
            }
        })
    }

}