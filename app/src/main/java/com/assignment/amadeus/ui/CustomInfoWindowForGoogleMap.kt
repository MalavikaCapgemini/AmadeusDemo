package com.assignment.amadeus.ui

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.TextView
import com.assignment.amadeus.R
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker

class CustomInfoWindowForGoogleMap(
    context: Context,
    city: String,
    main: String?,
    description: String?,
    temp: Double?,
    speed: Double?,
) : GoogleMap.InfoWindowAdapter {

    var mContext = context
    var mWindow = (context as Activity).layoutInflater.inflate(R.layout.custom_info_window, null)
    val city = city
    val description = description
    val main = main
    val temp = temp
    val speed = speed
    private fun rendowWindowText(marker: Marker, view: View) {

        val tvCity = view.findViewById<TextView>(R.id.city)
        val tvDescription = view.findViewById<TextView>(R.id.description)
        val tvMain = view.findViewById<TextView>(R.id.main)
        val tvTemp = view.findViewById<TextView>(R.id.temp)
        val tvSpeed = view.findViewById<TextView>(R.id.speed)


        tvCity.text = "City: $city"
        tvDescription.text = "Description: $description"
        tvMain.text = "Main: $main"
        tvTemp.text = "Temp: $temp"
        tvSpeed.text = "Speed: $speed"

    }


    override fun getInfoContents(marker: Marker): View {
        rendowWindowText(marker, mWindow)
        return mWindow
    }

    override fun getInfoWindow(marker: Marker): View? {
        rendowWindowText(marker, mWindow)
        return mWindow
    }
}