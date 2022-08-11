package com.assignment.amadeus.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.assignment.amadeus.R
import com.assignment.amadeus.databinding.ActivityMapsBinding

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private lateinit var binding: ActivityMapsBinding

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

        binding = ActivityMapsBinding.inflate(layoutInflater)
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


        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap


        val sydney = LatLng(-34.0, 151.0)
        mMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }
}