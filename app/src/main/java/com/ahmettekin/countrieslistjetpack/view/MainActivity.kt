package com.ahmettekin.countrieslistjetpack.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ahmettekin.countrieslistjetpack.R
import io.reactivex.Single

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}