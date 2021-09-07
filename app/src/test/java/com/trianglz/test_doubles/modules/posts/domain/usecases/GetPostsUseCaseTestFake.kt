package com.trianglz.test_doubles.modules.posts.domain.usecases

import com.google.common.truth.Truth.assertThat
import com.trianglz.test_doubles.common.posts.data.repository.FakePostsRepo
import com.trianglz.test_doubles.modules.posts.domain.models.PostDomainModel
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

class GetPostsUseCaseTestFake{

    @Test
    fun getPosts_withNoDuplicates_isTrue() {

        runBlockingTest {

            val fakePostRepo = FakePostsRepo()
            val useCase=GetPostsUseCase(fakePostRepo)
            val post = PostDomainModel(id = 1, title = "title",body = "body")
            useCase.addPost(post)
            useCase.addPost(post)
            useCase.addPost(post)
            val result = useCase.getPosts()
            assertThat(result).containsNoDuplicates()
        }
    }

}