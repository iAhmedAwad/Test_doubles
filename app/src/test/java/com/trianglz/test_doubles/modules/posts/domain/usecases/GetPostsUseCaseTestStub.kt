package com.trianglz.test_doubles.modules.posts.domain.usecases

import com.google.common.truth.Truth.assertThat
import com.trianglz.test_doubles.modules.posts.data.repository.PostsRepoImplTestStub
import com.trianglz.test_doubles.modules.posts.domain.models.PostDomainModel
import com.trianglz.test_doubles.modules.posts.domain.repository.PostsRepo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

@ExperimentalCoroutinesApi
class GetPostsUseCaseTestStub {

    @Test
    fun getPosts_sizeIsFive_isTrue() {

        runBlockingTest {

            val repo = PostsRepoImplTestStub()
            val useCase = GetPostsUseCase(repo)

            val result = useCase.getFirstFivePosts()
            assertThat(result.size).isEqualTo(5)
        }
    }

    @Test
    fun getPosts_usingMockitoSizeIsFive_isTrue() {

        runBlockingTest {

            val repo = mock(PostsRepo::class.java)
            val useCase = GetPostsUseCase(repo)
            val posts = ArrayList<PostDomainModel>().apply {
                repeat(20) {
                    val post = PostDomainModel(it, "title", "body")
                    add(post)
                }
            }
            `when`(repo.getPosts()).thenReturn(posts)
            val result = useCase.getFirstFivePosts()
            assertThat(result.size).isEqualTo(5)
        }
    }
}