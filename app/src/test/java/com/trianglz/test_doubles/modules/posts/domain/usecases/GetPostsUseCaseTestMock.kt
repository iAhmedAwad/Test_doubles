package com.trianglz.test_doubles.modules.posts.domain.usecases

import com.trianglz.test_doubles.modules.posts.data.repository.PostsRepoImplTestMock
import com.trianglz.test_doubles.modules.posts.domain.models.PostDomainModel
import com.trianglz.test_doubles.modules.posts.domain.repository.PostsRepo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

@ExperimentalCoroutinesApi
class GetPostsUseCaseTestMock {


    private val post = PostDomainModel(1, "title1", "body1")

    @Test
    fun timesCalled_twoTimes_isTwo() {

        runBlockingTest {
            val repo = PostsRepoImplTestMock()
            val useCase = GetPostsUseCase(repo)
            useCase.addPost(post)
            useCase.addPost(post)
            repo.verify(post, 2)
        }
    }


    @Test
    fun getLastPostAdded_specificPost_isSpecificPost() {

        runBlockingTest {
            val repo = PostsRepoImplTestMock()
            val useCase = GetPostsUseCase(repo)
            val post2 = PostDomainModel(2, "title2", "body2")
            useCase.addPost(post)
            useCase.addPost(post2)
            repo.verify(post2, 2)
        }
    }



    @Test
    fun timesCalled_usingMockitoTwoTimes_isTwo() {

        runBlockingTest {
            val repo = mock(PostsRepo::class.java)
            val useCase = GetPostsUseCase(repo)
            useCase.addPost(post)
            verify(repo).addPost(post)
            useCase.addPost(post)
            //verify(repo).addPost(post)
            verify(repo,Mockito.times(2)).addPost(post)
        }
    }

}