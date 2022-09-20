package com.bernoune.lib.domain

import com.bernoune.lib.data.repository.WeatherRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations


@ExperimentalCoroutinesApi
class GetFavoritesCitiesFromLocalTest{

    private lateinit var sut: GetFavoritesCitiesFromLocal

    @Mock
    lateinit var repository: WeatherRepository


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        sut = GetFavoritesCitiesFromLocal(repository)
    }

    @Test
    fun `when use case is invoked, should get cities from repository` () = runBlockingTest {

        //When
        sut.execute()
        //Then verfily invocation
        Mockito.verify(repository).getFavoriteCitiesFromLocal()
    }


}


