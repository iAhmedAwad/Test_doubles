package com.trianglz.test_doubles.modules.posts.domain.usecases

import com.google.common.truth.Truth.assertThat
import com.trianglz.test_doubles.modules.posts.domain.models.PostDomainModel
import com.trianglz.test_doubles.modules.posts.domain.repository.PostsRepo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before


import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetPostsUseCaseTest {

    @Mock
    private lateinit var repo: PostsRepo

    @InjectMocks
    private lateinit var useCase: GetPostsUseCase

    //Make private
    // @Before
    //  fun setup() {
    //     MockitoAnnotations.openMocks(this)
    // }

    @Test
    fun execute_containsNoNull_isTrue() {
        runBlockingTest {

            val posts = ArrayList<PostDomainModel>().apply {
                repeat(5) { add(PostDomainModel(1, "title", "body")) }
            }

            `when`(repo.getPosts()).thenReturn(posts)

            assertThat(useCase.execute()).doesNotContain(null)
        }
    }
}