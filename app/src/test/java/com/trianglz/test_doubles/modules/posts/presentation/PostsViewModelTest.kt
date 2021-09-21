package com.trianglz.test_doubles.modules.posts.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.truth.Truth.assertThat
import com.trianglz.test_doubles.modules.posts.domain.models.PostDomainModel
import com.trianglz.test_doubles.modules.posts.domain.repository.PostsRepo
import com.trianglz.test_doubles.modules.posts.domain.usecases.GetPostsUseCase
import com.trianglz.test_doubles.testutils.MainCoroutineRule
import com.trianglz.test_doubles.testutils.getOrAwaitValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.times
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
//@RunWith(MockitoJUnitRunner::class)
class PostsViewModelTest {

    private val testCoroutineDispatcher = TestCoroutineDispatcher()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var postsViewModel: PostsViewModel

    @Mock
    private lateinit var repo: PostsRepo

    @InjectMocks
    private lateinit var useCase: GetPostsUseCase


    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testCoroutineDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun getPosts_listHasItems_isTrue() {
        runBlocking {
            val list = MutableList(5) {
                PostDomainModel(it, "title $it", "body $it")
            }

            `when`(repo.getPosts()).thenReturn(list)

            postsViewModel = PostsViewModel(useCase, testCoroutineDispatcher)
            postsViewModel.getPosts()
            Mockito.verify(repo, times(1)).getPosts()
            assertThat(postsViewModel.postsList.getOrAwaitValue()).isNotEmpty()

        }
    }
}