package com.assignment.amadeus.data

import android.content.Context
import android.util.Log
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.assignment.amadeus.data.local.CityDatabase
import com.assignment.amadeus.data.local.CityEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONException
import java.io.BufferedReader

class StartingCity(private val context: Context) : RoomDatabase.Callback() {
    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        CoroutineScope(Dispatchers.IO).launch {
            fillWithStartingCity(context)
        }
    }


    private suspend fun fillWithStartingCity(context: Context) {

        val dao = CityDatabase.getInstance(context)?.dao


        try {

            val cities = loadJSONArray(context)

            if (cities != null) {

                for (i in 0 until cities.length()) {


                    val item = cities.getJSONObject(i)

                    val cityItem = item.optJSONObject("city")
                    val coordObj = cityItem.optJSONObject("coord")
                    val mainItem = item.optJSONObject("main")
                    val windItem = item.optJSONObject("wind")
                    val weatherArr = item.optJSONArray("weather")
                    val weatherItem = item.optJSONObject(weatherArr[0].toString())
                    var weatherMain = ""
                    var weatherDescription = ""
                    if (weatherItem != null) {
                        weatherMain = weatherItem.optString("main")
                        weatherDescription = weatherItem.optString("description")
                    }

                    val cityEntity = CityEntity(
                        cityItem.optString("name"),
                        cityItem.optString("country"),
                        coordObj.optDouble("lon"),
                        coordObj.optDouble("lat"),
                        cityItem.optInt("zoom"),
                        item.optInt("time"),
                        mainItem.optDouble("temp"),
                        windItem.optDouble("speed"),
                        weatherMain,
                        weatherDescription
                    )


                    dao?.insertCity(cityEntity)
                }
            }
        } catch (e: JSONException) {
            Log.e("", "fillWithStartingCity: $e")
        }
    }


    private fun loadJSONArray(context: Context): JSONArray? {

        val inputStream = context.assets.open("weather_14.json")

        BufferedReader(inputStream.reader()).use {
            return JSONArray(it.readText())
        }
    }
}