package com.bernoune.lib.domain

import com.bernoune.lib.common.whenever
import com.bernoune.lib.data.remote.response.model.WeatherCityResponse
import com.bernoune.lib.data.repository.WeatherRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertNotEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations


class GetWeatherByCityTest {

    private lateinit var sut: GetCityWeather

    @Mock
    lateinit var repository: WeatherRepository


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        sut = GetCityWeather(repository)
    }


    @ExperimentalCoroutinesApi
    @Test
    fun `when lat and are double, should get not null`() = runBlockingTest {

        //Given
        val lat = 48.85
        val lng = 2.35
        val exclude = "minutely,hourly"
        val key = "19dfcf148c7b16eabce0b71284b2a8f7"
        val units = "metric"
        val lang = "en"

        val response = WeatherCityResponse()
        whenever(
            repository.getCityWeather(
                lat,
                lng,
                exclude,
                key,
                units,
                lang
            )
        )
            .thenReturn(response)

        val result = sut.execute(lat, lng)

        //Then
        assertNotEquals(null,  result)

    }

}

