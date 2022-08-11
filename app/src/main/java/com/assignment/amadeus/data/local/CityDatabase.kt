package com.assignment.amadeus.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.assignment.amadeus.data.StartingCity

@Database(entities = [CityEntity::class], version = 1)
abstract class CityDatabase : RoomDatabase() {
    abstract val dao: CityDao

    companion object {
        @Volatile
        private var instance: CityDatabase? = null

        fun getInstance(context: Context): CityDatabase? {
            if (instance == null) {
                synchronized(CityDatabase::class.java) {
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        CityDatabase::class.java,
                        "city"
                    )
                        .addCallback(StartingCity(context))
                        .build()
                }
            }
            return instance
        }
    }
}