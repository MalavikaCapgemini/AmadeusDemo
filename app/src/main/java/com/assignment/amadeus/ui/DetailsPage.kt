package com.assignment.amadeus.ui

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import com.assignment.amadeus.databinding.DetailsPageBinding


class DetailsPage : FragmentActivity() {

    private lateinit var binding: DetailsPageBinding
    private var cityName: String? = null
    private var country: String? = null
    private var main: String? = null
    private var description: String? = null
    private var zoom: Int? = null
    private var time: Int? = null
    private var longitude: Double? = null
    private var latitude: Double? = null
    private var temp: Double? = null
    private var speed: Double? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DetailsPageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val intent = intent
        cityName = intent.getStringExtra("cityName")
        country = intent.getStringExtra("country")
        main = intent.getStringExtra("main")
        description = intent.getStringExtra("description")
        zoom = intent.getIntExtra("zoom", 0)
        time = intent.getIntExtra("time", 0)
        longitude = intent.getDoubleExtra("longitude", 0.0)
        latitude = intent.getDoubleExtra("longitude", 0.0)
        temp = intent.getDoubleExtra("longitude", 0.0)
        speed = intent.getDoubleExtra("longitude", 0.0)


    }


}