package com.trianglz.test_doubles.modules.posts.domain.usecases

import com.google.common.truth.Truth.assertThat
import com.trianglz.test_doubles.modules.posts.domain.models.PostDomainModel
import com.trianglz.test_doubles.modules.posts.domain.repository.PostsRepo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.InjectMocks
import org.mockito.Mockito.doReturn
import org.mockito.Spy
import org.mockito.junit.MockitoJUnitRunner


@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GetPostsUseCaseTest {

    @InjectMocks
    lateinit var useCase: GetPostsUseCase

    @Spy
    lateinit var repo: PostsRepo

    @Test
    fun getFirstPostId() {

        runBlockingTest {

            val postList = MutableList(5) {
                PostDomainModel(it + 1, "title $it", "body $it")
            }

           doReturn(postList).`when`(repo).getPosts()
            val actualFirstPostId = useCase.getFirstPostId()

            assertThat(actualFirstPostId).isEqualTo(1)

        }
    }
}
