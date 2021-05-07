package com.ahmettekin.countrieslistjetpack.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahmettekin.countrieslistjetpack.R
import com.ahmettekin.countrieslistjetpack.adapter.CountryAdapter
import com.ahmettekin.countrieslistjetpack.viewmodel.FeedViewModel
import kotlinx.android.synthetic.main.fragment_feed.*

class FeedFragment : Fragment() {

    private lateinit var viewModel: FeedViewModel
    private val countryAdapter = CountryAdapter(arrayListOf())

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(FeedViewModel::class.java)
        viewModel.refreshData()

        rvCountryList.layoutManager = LinearLayoutManager(context)
        rvCountryList.adapter = countryAdapter

        swipeRefreshLayout.setOnRefreshListener {
            rvCountryList.visibility = View.GONE
            tvCountryError.visibility = View.GONE
            pbCountryLoading.visibility = View.VISIBLE
            viewModel.refreshData()
            swipeRefreshLayout.isRefreshing = false
        }

        observeLiveData()

    }

    private fun observeLiveData(){

        viewModel.countries.observe(viewLifecycleOwner, { countries ->
                countries?.let {
                    rvCountryList.visibility = View.VISIBLE
                    countryAdapter.updateCountryList(countries)
                }

        })

        viewModel.countryError.observe(viewLifecycleOwner, { error->

            error?.let {
                if(it){
                    tvCountryError.visibility = View.VISIBLE
                    pbCountryLoading.visibility = View.GONE
                    rvCountryList.visibility = View.GONE
                }else{
                    tvCountryError.visibility = View.GONE
                }
            }

        })

        viewModel.countryLoading.observe(viewLifecycleOwner, { loading ->
            loading?.let {
                if(it){
                    pbCountryLoading.visibility = View.VISIBLE
                    rvCountryList.visibility = View.GONE
                    tvCountryError.visibility = View.GONE
                }else{
                    pbCountryLoading.visibility = View.GONE
                }
            }
        })

    }

}