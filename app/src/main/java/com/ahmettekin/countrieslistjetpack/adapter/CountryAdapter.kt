package com.ahmettekin.countrieslistjetpack.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ahmettekin.countrieslistjetpack.R
import com.ahmettekin.countrieslistjetpack.model.Country
import com.ahmettekin.countrieslistjetpack.view.FeedFragmentDirections
import kotlinx.android.synthetic.main.item_country.view.*

class CountryAdapter(private val countryList: ArrayList<Country>) :
    RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    class CountryViewHolder(var view: View) : RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CountryViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_country, parent, false)
        )


    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.view.tvName.text = countryList[position].countryName
        holder.view.tvRegion.text = countryList[position].countryRegion

        holder.view.setOnClickListener {
            val action = FeedFragmentDirections.actionFeedFragmentToCountryFragment()
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount() = countryList.size

    fun updateCountryList(newCountryList: List<Country>) {
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
    }

}