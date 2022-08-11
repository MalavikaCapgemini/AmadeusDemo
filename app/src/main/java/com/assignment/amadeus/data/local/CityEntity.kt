package com.assignment.amadeus.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "City_table")
data class CityEntity(
    val cityName:String,
    val country:String,
    val longitude: Double,
    val latitude: Double,
    val zoom: Int,
    val time: Int,
    val temp: Double,
    val speed: Double,
    val main: String,
    val description: String,
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
)

/* val longitude: Double,
    val latitude: Double,
    val zoom: Int,
    val time: Int,
    val temp: Double,
    val speed: Double,
    val main: String,
    val description: String,*/