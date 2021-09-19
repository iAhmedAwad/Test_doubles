package com.trianglz.test_doubles.modules.posts.domain.usecases

import com.google.common.truth.Truth.assertThat
import com.trianglz.test_doubles.modules.posts.data.repository.PostsRepoImplTestSpy
import com.trianglz.test_doubles.modules.posts.domain.models.PostDomainModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetPostsUseCaseTestSpy {

    lateinit var repo: PostsRepoImplTestSpy
    lateinit var useCase: GetPostsUseCase
    private val post = PostDomainModel(1, "title1", "body1")
    @Before
    fun setup() {
        repo = PostsRepoImplTestSpy()
        useCase = GetPostsUseCase(repo)
    }

    @Test
    fun timesCalled_twoTimes_isTwo() {
        runBlockingTest {

            useCase.addPost(post)
            useCase.addPost(post)

            assertThat(repo.getTimesCalled()).isEqualTo(2)

        }
    }

    @Test
    fun getLastPostAdded_specificPost_isSpecificPost() {
        runBlockingTest {

            val post2 = PostDomainModel(2, "title2", "body2")
            useCase.addPost(post)
            useCase.addPost(post2)

            assertThat(repo.getLastPostAdded()).isEqualTo(post2)
        }
    }
}