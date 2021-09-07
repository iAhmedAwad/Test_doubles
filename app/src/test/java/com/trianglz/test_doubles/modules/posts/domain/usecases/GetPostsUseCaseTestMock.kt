package com.trianglz.test_doubles.modules.posts.domain.usecases

import com.trianglz.test_doubles.modules.posts.data.repository.PostsRepoImplTestMock
import com.trianglz.test_doubles.modules.posts.domain.models.PostDomainModel
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

class GetPostsUseCaseTestMock {

    lateinit var repo: PostsRepoImplTestMock
    lateinit var useCase: GetPostsUseCase
    private val post = PostDomainModel(1, "title1", "body1")

    @Before
    fun setup() {
        repo = PostsRepoImplTestMock()
        useCase = GetPostsUseCase(repo)
    }

    @Test
    fun timesCalled_twoTimes_isTwo() {

        runBlockingTest {
            useCase.addPost(post)
            useCase.addPost(post)
            repo.verify(post, 2)
        }
    }

    @Test
    fun getLastPostAdded_specificPost_isSpecificPost() {

        runBlockingTest {
            val post2 = PostDomainModel(2, "title2", "body2")
            useCase.addPost(post)
            useCase.addPost(post2)
            repo.verify(post2, 2)
        }
    }
}