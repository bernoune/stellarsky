package com.bernoune.lib.data.local

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.bernoune.lib.data.entities.City
import com.bernoune.lib.data.local.dao.CityDao
import org.junit.Before
import org.junit.Rule
import org.mockito.Mock
import org.mockito.Mockito.spy

class CityDaoTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var cityDao: CityDao

    @Mock
    lateinit var database: AppDatabase

    private val cityA = City("Lyon", "A",1.0,1.0)
    private val cityB = City("Malmo","B",2.0,2.0)
    private val cityC = City("Paris", "C",3.0,3.0)



    @Before
    fun setUp() {
        cityDao = spy(database.cityDao)
    }

    // TODO: Complete tests



}