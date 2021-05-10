package com.ahmettekin.countrieslistjetpack.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.ahmettekin.countrieslistjetpack.R
import com.ahmettekin.countrieslistjetpack.adapter.CountryAdapter
import com.ahmettekin.countrieslistjetpack.adapter.CountryClickListener
import com.ahmettekin.countrieslistjetpack.databinding.FragmentFeedBinding
import com.ahmettekin.countrieslistjetpack.viewmodel.FeedViewModel

class FeedFragment : Fragment() ,CountryClickListener{
    private lateinit var viewModel: FeedViewModel
    private lateinit var databinding: FragmentFeedBinding
    private val countryAdapter = CountryAdapter(arrayListOf(),this)

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        databinding = DataBindingUtil.inflate(inflater, R.layout.fragment_feed,container,false)
        return databinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(FeedViewModel::class.java)
        viewModel.refreshData()
        databinding.rvCountryList.layoutManager = LinearLayoutManager(context)
        databinding.rvCountryList.adapter = countryAdapter
        databinding.swipeRefreshLayout.setOnRefreshListener {
            databinding.rvCountryList.visibility = View.GONE
            databinding.tvCountryError.visibility = View.GONE
            databinding.pbCountryLoading.visibility = View.VISIBLE
            viewModel.refreshFromAPI()
            databinding.swipeRefreshLayout.isRefreshing = false
        }
        observeLiveData()

    }

    private fun observeLiveData(){
        viewModel.countries.observe(viewLifecycleOwner, { countries ->
                countries?.let {
                    databinding.rvCountryList.visibility = View.VISIBLE
                    countryAdapter.updateCountryList(countries)
                }
        })
        viewModel.countryError.observe(viewLifecycleOwner, { error->
            error?.let {
                if(it){
                    databinding.tvCountryError.visibility = View.VISIBLE
                    databinding.pbCountryLoading.visibility = View.GONE
                    databinding.rvCountryList.visibility = View.GONE
                }else{
                    databinding.tvCountryError.visibility = View.GONE
                }
            }
        })
        viewModel.countryLoading.observe(viewLifecycleOwner, { loading ->
            loading?.let {
                if(it){
                    databinding.pbCountryLoading.visibility = View.VISIBLE
                    databinding.rvCountryList.visibility = View.GONE
                    databinding.tvCountryError.visibility = View.GONE
                }else{
                    databinding.pbCountryLoading.visibility = View.GONE
                }
            }
        })
    }

    override fun onCountryClicked(v: View) {
        v as LinearLayout
        val clickedCountryUuid = (v.getChildAt(0) as TextView).text.toString().toInt()
        val action = FeedFragmentDirections.actionFeedFragmentToCountryFragment(clickedCountryUuid)
        Navigation.findNavController(v).navigate(action)
    }



}