package com.assignment.amadeus.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.PopupWindow
import androidx.appcompat.app.AppCompatActivity
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
    private var mPopupWindow: PopupWindow? = null
    private var mWidth = 0
    private var mHeight = 0
    private var mMarker: Marker? = null

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
        mMap.uiSettings.isZoomControlsEnabled = true
        mMap.setOnMarkerClickListener(this)
        val myPlace = LatLng(latitude, longitude)
        mMap.addMarker(MarkerOptions().position(myPlace).title("Marker in $myPlace"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myPlace, zoom.toFloat()))
    }

    @SuppressLint("RestrictedApi")
    override fun onMarkerClick(marker: Marker): Boolean {
        /* if (mPopupWindow != null) {
             mPopupWindow!!.dismiss()
         }
         var popupView: View?= null// inflate our view here

         val popupWindow = PopupWindow(popupView,
             ViewGroup.LayoutParams.WRAP_CONTENT,
             ViewGroup.LayoutParams.WRAP_CONTENT)

         val display = ContextUtils.getActivity()!!.windowManager.defaultDisplay
         val size = Point()
         display.getSize(size)
         popupView?.measure(size.x, size.y)

         if (popupView != null) {
             mWidth = popupView.getMeasuredWidth()
             mHeight = popupView.getMeasuredHeight()
         }

         mMarker = marker
         mPopupWindow = popupWindow*/


        return false
    }
}