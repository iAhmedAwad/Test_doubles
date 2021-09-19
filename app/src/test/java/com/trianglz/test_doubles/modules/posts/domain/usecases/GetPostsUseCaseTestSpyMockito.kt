package com.trianglz.test_doubles.modules.posts.domain.usecases

import com.google.common.truth.Truth.assertThat
import com.trianglz.test_doubles.modules.posts.data.repository.PostsRepoImplTestSpy
import com.trianglz.test_doubles.modules.posts.domain.models.PostDomainModel
import com.trianglz.test_doubles.modules.posts.domain.repository.PostsRepo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

@ExperimentalCoroutinesApi
class GetPostsUseCaseTestSpyMockito {

    lateinit var repo: PostsRepo
    lateinit var useCase: GetPostsUseCase
    private val post = PostDomainModel(1, "title1", "body1")
    @Before
    fun setup() {
        repo = spy(PostsRepo::class.java)
        useCase = GetPostsUseCase(repo)
    }

    @Test
    fun timesCalled_twoTimes_isTwo() {
        runBlockingTest {

            useCase.addPost(post)
            useCase.addPost(post)

            verify(repo, times(2)).addPost(post)

        }
    }

}