package com.assignment.amadeus.ui

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.assignment.amadeus.R
import com.assignment.amadeus.databinding.ActivityMapsBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions


class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener {
    companion object {
        private const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }

    private lateinit var mMap: GoogleMap

    private lateinit var binding: ActivityMapsBinding

    private var cityName: String? = null
    private var country: String? = null
    private var main: String? = null
    private var description: String? = null
    private var zoom: Int = 0
    private var time: Int? = null
    private var longitude: Double = 0.0
    private var latitude: Double = 0.0
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
        temp = intent.getDoubleExtra("temp", 0.0)
        speed = intent.getDoubleExtra("speed", 0.0)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.setOnMarkerClickListener(this)
        val location = LatLng(latitude, longitude)
        mMap.addMarker(MarkerOptions().position(location))//.title("Marker in $cityName")
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, zoom.toFloat()))
        setUpMap()


    }

    @SuppressLint("RestrictedApi")
    override fun onMarkerClick(marker: Marker): Boolean {

        mMap.setInfoWindowAdapter(CustomInfoWindowForGoogleMap(this,
            "Marker in $cityName",
            main,
            description,
            temp,
            speed))
        return false
    }

    fun onInfoWindowClick(marker: Marker?) {
        // Perform event on click of info window of a marker
    }

    private fun setUpMap() {
        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE)
            return
        }

        mMap.isMyLocationEnabled = true

    }
}