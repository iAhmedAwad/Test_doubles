package com.trianglz.test_doubles.modules.posts.domain.usecases

import com.google.common.truth.Truth.assertThat
import com.trianglz.test_doubles.common.posts.data.repository.FakePostsRepo
import com.trianglz.test_doubles.modules.posts.domain.models.PostDomainModel
import com.trianglz.test_doubles.modules.posts.domain.repository.PostsRepo
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock


class GetPostsUseCaseTestFake {

    @ExperimentalCoroutinesApi
    @Test
    fun getPosts_withNoDuplicates_isTrue() {

        runBlockingTest {

            val fakePostRepo = FakePostsRepo()
            val useCase = GetPostsUseCase(fakePostRepo)
            val post = PostDomainModel(id = 1, title = "title", body = "body")
            useCase.addPost(post)
            useCase.addPost(post)
            useCase.addPost(post)
            val result = useCase.getPosts()
            assertThat(result).containsNoDuplicates()
        }
    }


    @Test
    fun getPosts_withNoDuplicatesUsingMockito_isTrue() {

        runBlockingTest {

            val postRepo = mock(PostsRepo::class.java)
            val useCase = GetPostsUseCase(postRepo)
            val post = PostDomainModel(id = 1, title = "title", body = "body")
            val posts = ArrayList<PostDomainModel>().apply {
                repeat(5) { add(post) }
            }
            `when`(postRepo.getPosts()).thenReturn(posts)
            val result = useCase.getPosts()
            assertThat(result).containsNoDuplicates()
        }

    }

}