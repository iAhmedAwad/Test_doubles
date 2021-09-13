package com.trianglz.test_doubles.modules.posts.data.repository


import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.haroldadmin.cnradapter.NetworkResponseAdapterFactory
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import com.trianglz.test_doubles.modules.posts.data.service.PostsService
import com.trianglz.test_doubles.modules.posts.domain.usecases.GetPostsUseCase
import com.trianglz.test_doubles.modules.posts.presentation.PostsViewModel
import com.trianglz.test_doubles.testutils.MainCoroutineRule
import com.trianglz.test_doubles.util.getOrAwaitValue
import com.trianglz.test_doubles.utils.Constants
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import javax.inject.Inject

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)

class PostsRepoImplTest @Inject constructor() {

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var mockWebServer: MockWebServer

    lateinit var service: PostsService

    lateinit var postsRepoImpl: PostsRepoImpl

    lateinit var useCase: GetPostsUseCase

    lateinit var viewModel: PostsViewModel

    private val testDispatcher = TestCoroutineDispatcher()


    @Before
    fun setup() {
        // 1
        Dispatchers.setMain(testDispatcher)
        mockWebServer = MockWebServer()
        mockWebServer.start()
        initiateMembers()
    }

    @After
    fun tearDown() {
        // 2
        Dispatchers.resetMain()
        // 3
        testDispatcher.cleanupTestCoroutines()
        mockWebServer.shutdown()
    }

    @Test
    fun `getPosts from repo WHEN getPosts return expected response`() {
        runBlocking {

            mockWebServer.enqueue(MockResponse().setResponseCode(200).setBody(getJson("success_resp_list.json")))
            val dataReceived = postsRepoImpl.getPosts()
            //Assert.assertNotNull(dataReceived)
            assertThat(dataReceived).isNotNull()
            assertThat(dataReceived.size).isEqualTo(100)


        }

    }

    @Test
    fun `getPosts from use-case WHEN getPosts return expected response`() {
        runBlocking {

            mockWebServer.enqueue(MockResponse().setResponseCode(200).setBody(getJson("success_resp_list.json")))
            val dataReceived = useCase.execute()
            //Assert.assertNotNull(dataReceived)
            assertThat(dataReceived).isNotNull()
            assertThat(dataReceived.size).isEqualTo(100)


        }

    }


    @Test
    fun `getPosts from viewModel WHEN getPosts return expected response`() {
        runBlocking {

            mockWebServer.enqueue(MockResponse().setResponseCode(200).setBody(getJson("success_resp_list.json")))
            val dataReceived = viewModel.postsList.getOrAwaitValue()
            //Assert.assertNotNull(dataReceived)
            assertThat(dataReceived).isNotNull()
            assertThat(dataReceived.size).isEqualTo(100)


        }

    }



    private fun initiateMembers() {
        val retrofit = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().add(
                        KotlinJsonAdapterFactory()
                    ).build()
                )
            )
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .build()
        service = retrofit.create(PostsService::class.java)
        postsRepoImpl= PostsRepoImpl(service)
        useCase= GetPostsUseCase(postsRepoImpl)
        viewModel =
            PostsViewModel(useCase)


    }

    private fun getJson(path : String) : String {
        val uri = javaClass.classLoader!!.getResource(path)
        val file = File(uri.path)
        return String(file.readBytes())
    }

}