package com.assignment.amadeus.data.local

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface CityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCity(cityEntity: CityEntity)

    @Delete
    suspend fun deleteCity(cityEntity: CityEntity)

    @Update
    suspend fun update(cityEntity: CityEntity)

    @Query("SELECT * FROM City_table ORDER BY id ASC")
    fun getAllCity(): LiveData<List<CityEntity>>

    @Query("SELECT * FROM City_table WHERE cityName LIKE :cityName ORDER BY id ASC")
    fun getSearchCities(cityName: String): LiveData<List<CityEntity>>
}