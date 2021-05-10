package com.ahmettekin.countrieslistjetpack.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ahmettekin.countrieslistjetpack.R
import com.ahmettekin.countrieslistjetpack.databinding.ItemCountryBinding
import com.ahmettekin.countrieslistjetpack.model.Country
import com.ahmettekin.countrieslistjetpack.view.FeedFragmentDirections

class CountryAdapter(private val countryList: ArrayList<Country>, private val mListener: CountryClickListener) : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>(){
    private lateinit var binding: ItemCountryBinding

    class CountryViewHolder(var binding: ItemCountryBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        binding = DataBindingUtil.inflate(inflater, R.layout.item_country, parent,false)
        return CountryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.binding.country = countryList[position]
        holder.binding.listener = mListener
    }

    override fun getItemCount() = countryList.size

    fun updateCountryList(newCountryList: List<Country>) {
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
    }

}