package com.bernoune.lib.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bernoune.lib.data.entities.City



@Dao
interface CityDao {

    @Query("SELECT * FROM cities")
    suspend fun findAll(): List<City>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(city: City)
}