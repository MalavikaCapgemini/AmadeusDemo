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
import timber.log.Timber
import java.io.BufferedReader

class StartingCity(private val context: Context) : RoomDatabase.Callback() {
    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        CoroutineScope(Dispatchers.IO).launch {
            fillWithStartingCity(context)
        }
    }

    //Filling database with the data from JSON
    private suspend fun fillWithStartingCity(context: Context) {
        //obtaining instance of data access object
        val dao = CityDatabase.getInstance(context)?.dao

        // using try catch to load the necessary data
        try {
            //creating variable that holds the loaded data
            val cities = loadJSONArray(context)
            Log.e("cities", "==>" + cities)
            if (cities != null) {
                //looping through the variable as specified fields are loaded with data
                for (i in 0 until cities.length()) {

                    //variable to obtain the JSON object
                    val item = cities.getJSONObject(i)
                    //Using the JSON object to assign data
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
/*,
                        coordObj.optDouble("lon"),
                        coordObj.optDouble("lat"),
                        cityItem.optInt("zoom"),
                        item.optInt("time"),
                        mainItem.optDouble("temp"),
                        windItem.optDouble("speed"),
                        weatherMain,
                        weatherDescription*/
                    //data loaded to the entity
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

                    //using dao to insert data to the database
                    dao?.insertCity(cityEntity)
                }
            }
        }
        //error when exception occurs
        catch (e: JSONException) {
            Timber.d("fillWithStartingCity: $e")
        }
    }

    // loads JSON data
    private fun loadJSONArray(context: Context): JSONArray? {
        //obtain input byte
        val inputStream = context.assets.open("weather_14.json")
        //using Buffered reader to read the inputstream byte
        BufferedReader(inputStream.reader()).use {
            return JSONArray(it.readText())
        }
    }
}