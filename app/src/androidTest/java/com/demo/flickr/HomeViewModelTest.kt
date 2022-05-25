package com.demo.flickr

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import com.demo.flickr.data.source.RemoteDataSourceImpl
import com.demo.flickr.domain.HomeRepository
import com.demo.flickr.ui.viewmodel.HomeViewModel
import com.demo.flickr.util.TestCoroutineRule
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.Mock
import org.mockito.Mockito.mock
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.ExperimentalCoroutinesApi

@RunWith(MockitoJUnitRunner::class)
class HomeViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    private lateinit var lifecycle: LifecycleRegistry

    @Mock
    private lateinit var viewModel: HomeViewModel

    private var mainRepository: HomeRepository = mock()

    private var remoteDataSource: RemoteDataSourceImpl = mock()

    @Before
    fun setup() {
        viewModel = HomeViewModel(mainRepository)
        lifecycle = LifecycleRegistry(mock(LifecycleOwner::class.java))
        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun givenServerResponse200_whenFetch_shouldReturnSuccess() {
        testCoroutineRule.runBlockingTest {
            val response = remoteDataSource.getAPIData("Electrolux")
            Assert.assertTrue(response.isSuccessful)
        }

    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun givenServerResponseError_whenFetch_shouldReturnError() {
        testCoroutineRule.runBlockingTest {
            val response = remoteDataSource.getAPIData("Electrolux")
            Assert.assertTrue(!response.isSuccessful)
        }
    }


    @Test
    fun testPhotosListOutput(){
        viewModel.photosList.observe({ lifecycle }, {
            Assert.assertTrue(!it.isNullOrEmpty())
        })
    }







}