package com.ahmettekin.countrieslistjetpack.util

import android.content.Context
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.databinding.BindingAdapter
import androidx.navigation.Navigation
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.ahmettekin.countrieslistjetpack.R
import com.ahmettekin.countrieslistjetpack.model.Country
import com.ahmettekin.countrieslistjetpack.view.FeedFragmentDirections
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

fun ImageView.downloadFromUrl(url: String?){

    val options = RequestOptions()
        .placeholder(placeholderProgressBar(context))
        .error(R.color.design_default_color_background)

    Glide.with(context)
        .setDefaultRequestOptions(options)
        .load(url)
        .into(this)

}

@BindingAdapter("android:downloadUrl")
fun downloadImage(view: ImageView, url: String?){
    view.downloadFromUrl(url)
}

/*@BindingAdapter("android:onItemClick")
fun configureListener(view: LinearLayout , country:Country){
    view.setOnClickListener {
        val action = FeedFragmentDirections.actionFeedFragmentToCountryFragment(country.uuid)
        Navigation.findNavController(it).navigate(action)
    }
}*/

private fun placeholderProgressBar(context: Context) : CircularProgressDrawable {
    return CircularProgressDrawable(context).apply {
        strokeWidth = 8f
        centerRadius = 40f
        start()
    }
}