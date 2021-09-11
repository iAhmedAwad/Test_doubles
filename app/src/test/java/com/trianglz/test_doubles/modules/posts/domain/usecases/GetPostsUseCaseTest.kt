package com.trianglz.test_doubles.modules.posts.domain.usecases

import com.trianglz.test_doubles.modules.posts.domain.models.PostDomainModel
import com.trianglz.test_doubles.modules.posts.domain.repository.PostsRepo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito.never
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetPostsUseCaseTest {

    @Mock
    private lateinit var repo: PostsRepo

    @InjectMocks
    private lateinit var useCase: GetPostsUseCase


    @Test
    fun addPost_verifyMethodCalled_isTrue() {

        runBlockingTest {

            val post = PostDomainModel(1, "title", "body")

            useCase.addPost(post)

            verify(repo).addPost(post)
        }
    }

    //Times of interactions?
    @Test
    fun addPost_verifyMethodCalledZeroTimes_isTrue() {

        runBlockingTest {

            val post = PostDomainModel(1, "title", "body")

            useCase.addPost(post)

            verify(repo, times(1)).addPost(post)
        }
    }

    @Test
    fun addPost_verifyMethodNeverCalled_isTrue() {

        runBlockingTest {

            val post = PostDomainModel(1, "title", "body")

            useCase.addPost(post)

            verify(repo, never()).addPost(post)
        }
    }

}