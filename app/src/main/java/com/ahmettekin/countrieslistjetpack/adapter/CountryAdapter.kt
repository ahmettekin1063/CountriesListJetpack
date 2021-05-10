package com.ahmettekin.countrieslistjetpack.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ahmettekin.countrieslistjetpack.R
import com.ahmettekin.countrieslistjetpack.databinding.ItemCountryBinding
import com.ahmettekin.countrieslistjetpack.model.Country
import com.ahmettekin.countrieslistjetpack.view.FeedFragmentDirections
import kotlinx.android.synthetic.main.item_country.view.*

class CountryAdapter(private val countryList: ArrayList<Country>) : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>(), CountryClickListener {

    class CountryViewHolder(var view: ItemCountryBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemCountryBinding>(inflater, R.layout.item_country, parent,false)
        return CountryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.view.country = countryList[position]
        holder.view.listener = this
        //holder.view.tvName.text = countryList[position].countryName
        //holder.view.tvRegion.text = countryList[position].countryRegion
        //
        //holder.view.setOnClickListener {
        //    val action = FeedFragmentDirections.actionFeedFragmentToCountryFragment(countryList[position].uuid)
        //    Navigation.findNavController(it).navigate(action)
        //}
        //
        //holder.view.imgCountryFlag.downloadFromUrl(countryList[position].imageUrl)
    }

    override fun getItemCount() = countryList.size

    fun updateCountryList(newCountryList: List<Country>) {
        countryList.clear()
        countryList.addAll(newCountryList)
        notifyDataSetChanged()
    }

    override fun onCountryClicked(v: View) {
        val action = FeedFragmentDirections.actionFeedFragmentToCountryFragment(v.tvCountryUuid.text.toString().toInt())
        Navigation.findNavController(v).navigate(action)
    }

}