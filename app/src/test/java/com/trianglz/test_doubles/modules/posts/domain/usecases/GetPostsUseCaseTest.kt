package com.trianglz.test_doubles.modules.posts.domain.usecases

import com.trianglz.test_doubles.modules.posts.domain.models.PostDomainModel
import com.trianglz.test_doubles.modules.posts.domain.repository.PostsRepo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InOrder
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.inOrder
import org.mockito.Mockito.never
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyNoInteractions
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetPostsUseCaseTest {

    @Mock
    private lateinit var repo: PostsRepo

    @InjectMocks
    private lateinit var useCase: GetPostsUseCase

    @Test
    fun testBehaviour_createPostBeforeAddPost_isTrue() {

        runBlockingTest {

            val post = PostDomainModel(1, "title", "body")

            `when`(repo.checkPostValidity(post)).thenReturn(true)

            useCase.addPost(post)

            verify(repo).addPost(post)
            verify(repo).checkPostValidity(post)

            // val inOrder = inOrder(repo)
            //
            // inOrder.verify(repo).addPost(post)
            // inOrder.verify(repo).checkPostValidity(post)

        }
    }
}