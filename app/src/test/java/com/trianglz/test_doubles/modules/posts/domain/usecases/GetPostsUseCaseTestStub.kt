package com.trianglz.test_doubles.modules.posts.domain.usecases

import com.google.common.truth.Truth.assertThat
import com.trianglz.test_doubles.modules.posts.data.repository.PostsRepoImplTestStub
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

class GetPostsUseCaseTestStub {

    @Test
    fun getPosts_sizeIsFive_isTrue() {

        runBlockingTest {

            val repo = PostsRepoImplTestStub()
            val useCase=GetPostsUseCase(repo)

            val result = useCase.getFirstFivePosts()
            assertThat(result.size).isEqualTo(5)
        }
    }
}