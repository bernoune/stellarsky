package com.bernoune.lib.domain

import com.bernoune.lib.common.whenever
import com.bernoune.lib.data.repository.WeatherRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations


class GetWeatherInfoFromLocalTest {

    private lateinit var sut: GetWeatherInfoFromLocal

    @Mock
    lateinit var repository: WeatherRepository


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        sut = GetWeatherInfoFromLocal(repository)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `when lat and are zero, should get null`() = runBlockingTest {


        //Given
        val lat = 0.0
        val lng = 0.0

        val response = null
        whenever(
            repository.getWeatherInfoFromLocal(
                lat,
                lng
            )
        )
            .thenReturn(response)

        val result = sut.execute(lat, lng)

        //Then
        assertEquals(null, result)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun `when lat and lng doesn't exist, should get null`() = runBlockingTest {

        //Given
        val lat = 77.777
        val lng = 7.7

        val response = null
        whenever(
            repository.getWeatherInfoFromLocal(
                lat,
                lng
            )
        )
            .thenReturn(response)

        //When
        val result = sut.execute(lat, lng)

        //Then
        assertEquals(null, result)
    }


}