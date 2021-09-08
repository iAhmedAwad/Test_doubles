package com.trianglz.test_doubles.modules.posts.domain.usecases

import com.google.common.truth.Truth.assertThat
import com.trianglz.test_doubles.modules.posts.domain.models.PostDomainModel
import com.trianglz.test_doubles.modules.posts.domain.repository.PostsRepo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest


import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
// @RunWith(MockitoJUnitRunner::class)
class GetPostsUseCaseTest {

    // @Mock
    // private lateinit var repo: PostsRepo

    @Test
    fun execute_containsNoNull_isTrue() {
        runBlockingTest {
            
            val repo = mock(PostsRepo::class.java)
            val useCase = GetPostsUseCase(repo)

            val posts = ArrayList<PostDomainModel>().apply {
                repeat(5) { add(PostDomainModel(1, "title", "body")) }
            }

            `when`(repo.getPosts()).thenReturn(posts)

            assertThat(useCase.execute()).doesNotContain(null)
        }
    }
}